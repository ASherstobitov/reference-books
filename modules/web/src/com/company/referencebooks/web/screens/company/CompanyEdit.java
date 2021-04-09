package com.company.referencebooks.web.screens.company;

import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Company;

import javax.inject.Inject;

@UiController("referencebooks_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
@LoadDataBeforeShow
public class CompanyEdit extends StandardEditor<Company> {

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Company> event) {
        currentNumber = sequenceNumberService.getNextNumber("company");
        event.getEntity().setCode("ОРГ00000" + currentNumber);
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        sequenceNumberService.setCurrentNumber("company", currentNumber);
    }
}