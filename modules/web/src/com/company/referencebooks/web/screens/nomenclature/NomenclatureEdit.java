package com.company.referencebooks.web.screens.nomenclature;

import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Nomenclature;

import javax.inject.Inject;

@UiController("referencebooks_Nomenclature.edit")
@UiDescriptor("nomenclature-edit.xml")
@EditedEntityContainer("nomenclatureDc")
@LoadDataBeforeShow
public class NomenclatureEdit extends StandardEditor<Nomenclature> {

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Nomenclature> event) {
        currentNumber = sequenceNumberService.getNextNumber("nomenclature");
        event.getEntity().setCode(String.format("НД00000%s", currentNumber));
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        if (currentNumber != null)
        sequenceNumberService.setCurrentNumber("nomenclature", currentNumber);
    }
}