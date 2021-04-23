package com.company.referencebooks.web.screens.outgoingdocument;

import com.company.referencebooks.entity.*;
import com.company.referencebooks.service.CurrentDocumentStateService;
import com.company.referencebooks.service.OutgoingDocumentService;
import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.bpm.entity.*;
import com.haulmont.bpm.service.BpmEntitiesService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

import com.haulmont.bpm.gui.procactionsfragment.ProcActionsFragment;

@UiController("referencebooks_OutgoingDocument.edit")
@UiDescriptor("outgoing-document-edit.xml")
@EditedEntityContainer("outgoingDocumentDc")
@LoadDataBeforeShow
public class OutgoingDocumentEdit extends StandardEditor<OutgoingDocument> {
    @Inject
    private DateField<Date> sentWorkDateField;

    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private OutgoingDocumentService service;

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Inject
    private FileUploadingAPI fileUploadingAPI;

    @Inject
    private Notifications notifications;

    @Inject
    private DataManager dataManager;

    @Inject
    CollectionPropertyContainer<FileDescriptor> documentsDc;

    private static final String PROCESS_CODE = "copyOfDocumentApproval2";

    @Inject
    private CollectionLoader<ProcTask> procTasksDl;

    @Inject
    private InstanceContainer<OutgoingDocument> outgoingDocumentDc;

    @Inject
    protected ProcActionsFragment procActionsFragment;

    @Inject
    private InstanceLoader<OutgoingDocument> outgoingDocumentDl;

    @Inject
    private LookupPickerField<Employee> coordinatorField;

    @Inject
    protected BpmEntitiesService bpmEntitiesService;

    @Inject
    private Metadata metadata;

    @Inject
    private DateField<Date> startProcessDateField;

    @Inject
    private DateField<Date> endProcessDateField;

    @Inject
    private CollectionContainer<ProcTask> procTasksDc;

    @Inject
    private CurrentDocumentStateService currentDocumentStateService;

    @Inject
    private PickerField<Logbook> logbookField;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {

        outgoingDocumentDl.load();
        procTasksDl.setParameter("entityId", outgoingDocumentDc.getItem().getId());

        procActionsFragment.initializer()
                .standard()
                .setBeforeStartProcessPredicate(() -> {
                    ProcInstance procInstance = procActionsFragment.getProcInstance();
                    ProcActor initiatorProcActor = createProcActor("initiator", procInstance, getEditedEntity().getAuthor());
                    ProcActor managerProcActor = null;
                    try {
                        managerProcActor = createProcActor("manager", procInstance, getEditedEntity().getExecutor().getDepartment().getManager().getUser());
                    } catch (RuntimeException e) {
                        notifications.create(Notifications.NotificationType.ERROR)
                                .withCaption("У вашего подразделения не указан менеджер").show();
                        throw new RuntimeException("У вашего подразделения не указан менеджер");
                    }
                    ProcActor coordinatorsProcActor = createProcActor("coordinators", procInstance, getEditedEntity().getCoordinator().getUser());
                    ProcActor signerProcActor = createProcActor("signer", procInstance, getEditedEntity().getSigner().getUser());
                    Set<ProcActor> procActors = new HashSet<>();
                    procActors.add(initiatorProcActor);
                    procActors.add(managerProcActor);
                    procActors.add(coordinatorsProcActor);
                    procActors.add(signerProcActor);
                    procInstance.setProcActors(procActors);
                    return true;
                }).setAfterStartProcessListener(() -> {
            procTasksDl.load();
            getEditedEntity().setState(State.APPROVAL);
            procActionsFragment.getFragment().setEnabled(false);
            dataManager.commit(getEditedEntity());
        })
                .setAfterCompleteTaskListener(() -> {
                    procTasksDl.load();
                    procActionsFragment.getFragment().setEnabled(false);
                    ProcTask procTask = getCurrentState();

                    if (procTask.getOutcome() != null && procTask.getOutcome().equals("Зарегистрировать")) {
                        getEditedEntity().setState(State.REGISTERED);
                    } else if (procTask.getOutcome() != null && procTask.getOutcome().equals("Отменить")) {
                        getEditedEntity().setState(State.CANCELED);
                    }
                    dataManager.commit(getEditedEntity());
                })
                .init(PROCESS_CODE, getEditedEntity());
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<OutgoingDocument> event) {
        event.getEntity().setState(State.NEW);
        event.getEntity().setCreationDate(new Date());
        User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        Employee employee = service.getEmployeeByUserUuid(user.getUuid());
        if (employee != null) {
            event.getEntity().setExecutor(employee);
        }
        event.getEntity().setAuthor(user);
    }

    @Subscribe(id = "outgoingDocumentDc", target = Target.DATA_CONTAINER)
    public void updateFields(InstanceContainer.ItemPropertyChangeEvent<OutgoingDocument> event) {

        List<ProcTask> procTasks = procTasksDc.getMutableItems();

        if (procTasks.size() != 0) {
            startProcessDateField.setValue(procTasks.get(0).getStartDate());
        }

        if (getEditedEntity().getState() == State.REGISTERED || getEditedEntity().getState() == State.CANCELED) {
            procActionsFragment.getFragment().setEnabled(false);
            endProcessDateField.setValue(procTasks.get(procTasks.size() - 1).getEndDate());
        }

        User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

        Long countActualTasksForCurrentUser = procTasks.stream()
                .filter(procTask -> procTask.getEndDate() == null)
                .filter(procTask -> procTask.getProcActor().getUser().getUuid().equals(user.getUuid())).count();

        if (countActualTasksForCurrentUser > 0) {
            procActionsFragment.getFragment().setEnabled(true);
        }

        if (getEditedEntity().getState() == State.REGISTRATION
                && getEditedEntity().getRegistrationNumber() == null
                && countActualTasksForCurrentUser > 0) {
            logbookField.setEnabled(true);
            logbookField.setRequired(true);
        } else
            logbookField.setEnabled(false);


        if (getEditedEntity().getState() != State.NEW) {
            coordinatorField.setEditable(false);
        }

        OutgoingDocument document = event.getSource().getItem();
        String regNumber = document.getRegistrationNumber() != null ?
                "№ " + document.getRegistrationNumber() : "";
        String regDate = document.getRegistrationNumber() != null ?
                " от " + new SimpleDateFormat("dd/MM/yyyy")
                        .format(document.getRegistrationDate()) + " в " : "";
        String typeDoc = document.getTypeDocument() != null ?
                document.getTypeDocument().getName() + " " : "";
        String topic = document.getTopic() != null ?
                document.getTopic() : "";
        String company = document.getCompany() != null ?
                document.getCompany().getShortName() + ", " : "";
        event.getItem().setName(String.format("%s%s%s%s%s", typeDoc, regNumber, regDate, company, topic));
    }

    @Subscribe("nomenclatureCaseField")
    public void onNomenclatureCaseFieldValueChange(HasValue.ValueChangeEvent<Nomenclature> event) {
        sentWorkDateField.setValue(new Date());
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        if (currentNumber != null)
            sequenceNumberService.setCurrentNumber("outGoingDocument", currentNumber);
    }

    @Subscribe("multiUploadField")
    public void onMultiUploadFieldQueueUploadComplete(FileMultiUploadField.QueueUploadCompleteEvent event) {
        for (Map.Entry<UUID, String> entry : event.getSource().getUploadsMap().entrySet()) {
            UUID fileId = entry.getKey();
            String fileName = entry.getValue();
            FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
            try {
                fileUploadingAPI.putFileIntoStorage(fileId, fd);
            } catch (FileStorageException e) {
                throw new RuntimeException("Error saving file to FileStorage", e);
            }
            documentsDc.getMutableItems()
                    .add(dataManager.commit(fd));
        }
        notifications.create()
                .withCaption("Upload files: " + event.getSource().getUploadsMap().values()).show();
        event.getSource().clearUploads();
    }

    @Subscribe("logbookField")
    public void onLogbookFieldValueChange(HasValue.ValueChangeEvent<Logbook> event) {

        if (event.getValue() != null && getEditedEntity().getRegistrationNumber() == null) {
            String[] arr = event.getValue().getFormat().split(" - ");
            getEditedEntity().setRegistrationDate(new Date());
            String pattern = arr[1].contains("DD") ? arr[1].replace("DD", "dd") : arr[1];
            String newDate = new SimpleDateFormat(pattern).format(new Date());
            if (currentNumber != null) {
                sequenceNumberService.setCurrentNumber("outGoingDocument", currentNumber);
            }
            currentNumber = sequenceNumberService.getNextNumber("outGoingDocument");
            String strCurrentNum = String.valueOf(currentNumber);
            int lengthNum = event.getValue().getAmount() != null ? (int) (event.getValue().getAmount() - 1) : 0;

            String nulls = StringUtils.repeat('0', lengthNum);
            String formattedNumber = String.format("%s - %s - %s%s", arr[0], newDate, nulls, strCurrentNum);
            getEditedEntity().setRegistrationNumber(formattedNumber);
        }
    }

    @Subscribe("logbookField.clear")
    public void onLogbookFieldClear(Action.ActionPerformedEvent event) {
        getEditedEntity().setRegistrationNumber(null);
        getEditedEntity().setRegistrationDate(null);
        getEditedEntity().setLogbook(null);
        sequenceNumberService.setCurrentNumber("outGoingDocument", currentNumber);
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setChangingDate(new Date());
    }

    private ProcActor createProcActor(String procRoleCode, ProcInstance procInstance, User user) {
        ProcActor procActor = metadata.create(ProcActor.class);
        procActor.setUser(user);
        ProcRole initiatorProcRole = bpmEntitiesService.findProcRole(PROCESS_CODE, procRoleCode, View.MINIMAL);
        procActor.setProcRole(initiatorProcRole);
        procActor.setProcInstance(procInstance);
        return procActor;
    }

    private ProcTask getCurrentState() {
        List<ProcTask> procTasks = procTasksDl.getContainer().getMutableItems();
        ProcTask lastProcTasks = null;
        if (procTasks.size() != 0) {
            lastProcTasks = procTasks.get(procTasks.size() - 1);
            getEditedEntity().setState(currentDocumentStateService.setCurrentState(lastProcTasks.getName()));
        }
        return lastProcTasks;
    }
}

