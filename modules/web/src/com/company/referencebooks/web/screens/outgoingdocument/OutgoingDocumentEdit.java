package com.company.referencebooks.web.screens.outgoingdocument;

import com.company.referencebooks.entity.Nomenclature;
import com.company.referencebooks.entity.TypeDocument;
import com.company.referencebooks.service.OutgoingDocumentService;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.OutgoingDocument;

import javax.inject.Inject;
import java.text.DateFormat;
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
    private OutgoingDocumentService service;

    @Subscribe
    public void onInitEntity(InitEntityEvent<OutgoingDocument> event) {
        event.getEntity().setRegistrationNumber("И00000" + service.getNextValue());
        event.getEntity().setRegistrationDate(new Date());
        event.getEntity().setCreationDate(new Date());
    }


    @Subscribe(id = "outgoingDocumentDc", target = Target.DATA_CONTAINER)
    public void updateFields(InstanceContainer.ItemPropertyChangeEvent<OutgoingDocument> event) {

        OutgoingDocument document = event.getItem();


        if (document.getTypeDocument() != null && document.getCompany() != null
        && document.getTopic() != null) {

            event.getItem().setName(document.getRegistrationNumber() + " от "
                    + new SimpleDateFormat("dd/MM/yyyy").format(document.getRegistrationDate()) + " "
                    + document.getTypeDocument().getName() + " "
                    + document.getCompany().getShortName() + " "
                    + document.getTopic()
            );
        }

        if (document.getNomenclatureCase() != null) {
            event.getItem().setSentWorkDate(new Date());
        }
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
}