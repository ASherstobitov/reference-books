package com.company.referencebooks.web.screens.employee;

import com.company.referencebooks.entity.User;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Employee;

@UiController("referencebooks_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {

    @Subscribe(id = "employeeDc", target = Target.DATA_CONTAINER)
    public void updateFields(InstanceContainer.ItemPropertyChangeEvent<Employee> event) {

        User user = event.getItem().getUser();

        if (user != null) {
            event.getItem().setLastName(user.getLastName());
            event.getItem().setFirstName(user.getFirstName());
            event.getItem().setMiddleName(user.getMiddleName());
        }
    }

}