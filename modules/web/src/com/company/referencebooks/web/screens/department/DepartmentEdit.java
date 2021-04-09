package com.company.referencebooks.web.screens.department;

import com.company.referencebooks.service.SequenceNumberService;
import com.haulmont.cuba.gui.components.Button;
import com.company.referencebooks.entity.Department;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("referencebooks_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
@LoadDataBeforeShow
public class DepartmentEdit extends StandardEditor<Department> {

    @Inject
    private SequenceNumberService sequenceNumberService;

    private Long currentNumber;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Department> event) {
        currentNumber = sequenceNumberService.getNextNumber("department");
        event.getEntity().setCode("ОТД00000" + currentNumber);
    }

    @Subscribe("close")
    public void onCloseClick(Button.ClickEvent event) {
        sequenceNumberService.setCurrentNumber("department", currentNumber);
    }
}