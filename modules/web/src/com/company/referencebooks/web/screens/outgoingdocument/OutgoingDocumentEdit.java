package com.company.referencebooks.web.screens.outgoingdocument;

import com.company.referencebooks.entity.Employee;
import com.company.referencebooks.entity.Logbook;
import com.company.referencebooks.entity.Nomenclature;
import com.company.referencebooks.service.OutgoingDocumentService;
import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.company.referencebooks.entity.OutgoingDocument;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.Target;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@UiController("referencebooks_OutgoingDocument.edit")
@UiDescriptor("outgoing-document-edit.xml")
@EditedEntityContainer("outgoingDocumentDc")
@LoadDataBeforeShow
public class OutgoingDocumentEdit extends StandardEditor<OutgoingDocument> {

    @Inject
    private LookupField<String> conditionField;

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

    @Subscribe
    public void onInitEntity(InitEntityEvent<OutgoingDocument> event) {
        event.getEntity().setCreationDate(new Date());

        User user = userSessionSource.getUserSession().getUser();
        Employee employee = service.getEmployeeByUserUuid(user.getUuid());
        if (employee != null) {
            event.getEntity().setExecutor(employee);
        }
        event.getEntity().setAuthor(user);
    }

    @Subscribe(id = "outgoingDocumentDc", target = Target.DATA_CONTAINER)
    public void updateFields(InstanceContainer.ItemPropertyChangeEvent<OutgoingDocument> event) {

        OutgoingDocument document = event.getSource().getItem();
        String regNumber = document.getRegistrationNumber() != null ? "№ " + document.getRegistrationNumber() : "";
        String regDate = document.getRegistrationNumber() != null ?
                " от " + new SimpleDateFormat("dd/MM/yyyy").format(document.getRegistrationDate()) + " в " : "";
        String typeDoc = document.getTypeDocument() != null ? document.getTypeDocument().getName() + " " : "";
        String topic = document.getTopic() != null ? document.getTopic() : "";
        String company = document.getCompany() != null ? document.getCompany().getShortName() + ", " : "";
        event.getItem().setName(String.format("%s%s%s%s%s",typeDoc, regNumber, regDate, company, topic));
    }

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> list = new ArrayList<>();
        list.add("1.	Новый – заполняется при создании доку-мента");
        list.add("2.	На согласовании – на согласовании у ру-ководителя или согласующих");
        list.add("3.	На подписании – на подписании у подпи-санта");
        list.add("4.	На доработке");
        list.add("5.	Зарегистрирован");
        conditionField.setOptionsList(list);
    }

    @Subscribe(id = "outgoingDocumentDc", target = Target.DATA_CONTAINER)
    public void onOutgoingDocumentDcItemChange(InstanceContainer.ItemChangeEvent<OutgoingDocument> event) {
        event.getItem().setChangingDate(new Date());
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

        String creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(getEditedEntity().getCreationDate());
        String changingDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(getEditedEntity().getChangingDate());

        if (creationDate.equals(changingDate)) {
            if ( event.getValue() != null) {
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
                getEditedEntity().setRegistrationNumber(String.format("%s - %s - %s%s", arr[0], newDate, nulls, strCurrentNum));
            }
        }
        else
            event.getComponent().setEnabled(false);
    }

    @Subscribe("logbookField.clear")
    public void onLogbookFieldClear(Action.ActionPerformedEvent event) {
        getEditedEntity().setRegistrationNumber(null);
        getEditedEntity().setRegistrationDate(null);
        getEditedEntity().setLogbook(null);
        sequenceNumberService.setCurrentNumber("outGoingDocument", currentNumber);
    }
}