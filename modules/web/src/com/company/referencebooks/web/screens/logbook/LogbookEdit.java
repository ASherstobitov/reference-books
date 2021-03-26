package com.company.referencebooks.web.screens.logbook;

import com.company.referencebooks.service.LogbookService;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Logbook;

import javax.inject.Inject;

@UiController("referencebooks_Logbook.edit")
@UiDescriptor("logbook-edit.xml")
@EditedEntityContainer("logbookDc")
@LoadDataBeforeShow
public class LogbookEdit extends StandardEditor<Logbook> {

    @Inject
    private LogbookService logbookService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Logbook> event) {
        event.getEntity().setCode("Ж00000" + logbookService.getNextValue());
    }
}