package com.company.referencebooks.web.screens.logbook;

import com.company.referencebooks.entity.Nomenclature;
import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Logbook;

import javax.inject.Inject;

@UiController("referencebooks_Logbook.edit")
@UiDescriptor("logbook-edit.xml")
@EditedEntityContainer("logbookDc")
@LoadDataBeforeShow
public class LogbookEdit extends StandardEditor<Logbook> {

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Logbook> event) {
        currentNumber = sequenceNumberService.getNextNumber("logbook");
        event.getEntity().setCode("Ж00000" + currentNumber);
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        sequenceNumberService.setCurrentNumber("logbook", currentNumber);
    }
}