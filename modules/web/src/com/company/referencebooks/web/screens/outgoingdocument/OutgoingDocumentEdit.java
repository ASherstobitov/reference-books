package com.company.referencebooks.web.screens.outgoingdocument;

import com.company.referencebooks.entity.Employee;
import com.company.referencebooks.entity.Nomenclature;
import com.company.referencebooks.service.OutgoingDocumentService;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.OutgoingDocument;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Subscribe
    public void onInitEntity(InitEntityEvent<OutgoingDocument> event) {
        event.getEntity().setRegistrationNumber("И00000" + service.getNextValue());
        event.getEntity().setRegistrationDate(new Date());
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

        String numDate = "№ " + document.getRegistrationNumber() + " от "
                + new SimpleDateFormat("dd/MM/yyyy").format(document.getRegistrationDate()) ;

        String typeDoc = "";
        String topic = "";
        String company = "";

        if (document.getTypeDocument()!= null) {
            typeDoc = document.getTypeDocument().getName();
        }

        if (document.getTopic() != null) {
            topic = document.getTopic();
        }

        if (document.getCompany() != null) {
            company = document.getCompany().getShortName();
        }
        event.getItem().setName(typeDoc + " "
                + numDate + " в "
                + company + ", "
                + topic
        );

    }

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> list = new ArrayList<>();
        list.add("1.	Новый – заполняется при создании доку-мента");
        list.add ("2.	На согласовании – на согласовании у ру-ководителя или согласующих");
        list.add ("3.	На подписании – на подписании у подпи-санта");
        list.add ("4.	На доработке");
        list.add ("5.	Зарегистрирован");
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
}