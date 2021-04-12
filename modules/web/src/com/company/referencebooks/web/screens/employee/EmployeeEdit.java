package com.company.referencebooks.web.screens.employee;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Employee;
import com.haulmont.cuba.security.entity.User;
import javax.inject.Inject;

@UiController("referencebooks_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
    @Inject
    private TextField<String> lastNameField;

    @Inject
    private TextField<String> firstNameField;

    @Inject
    private TextField<String> middleNameField;

    @Subscribe("userField")
    public void onUserFieldValueChange(HasValue.ValueChangeEvent<User> event) {
        User user = event.getSource().getValue();
        if (user != null) {
            if (user.getLastName() != null)
                lastNameField.setValue(user.getLastName());
            if (user.getFirstName() != null)
                firstNameField.setValue(user.getFirstName());
            if (user.getMiddleName() != null)
                middleNameField.setValue(user.getMiddleName());
        }
    }
}