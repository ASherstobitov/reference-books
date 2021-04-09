package com.company.referencebooks.web.screens.typedocument;

import com.company.referencebooks.entity.Department;
import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.TypeDocument;

import javax.inject.Inject;

@UiController("referencebooks_TyprDocument.edit")
@UiDescriptor("type-document-edit.xml")
@EditedEntityContainer("typeDocumentDc")
@LoadDataBeforeShow
public class TypeDocumentEdit extends StandardEditor<TypeDocument> {

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Subscribe
    public void onInitEntity(InitEntityEvent<TypeDocument> event) {
        currentNumber = sequenceNumberService.getNextNumber("typeDocument");
        event.getEntity().setCode("ВД00000" + currentNumber);
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        sequenceNumberService.setCurrentNumber("typeDocument", currentNumber);
    }
}