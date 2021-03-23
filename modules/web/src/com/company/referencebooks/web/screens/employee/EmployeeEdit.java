package com.company.referencebooks.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Employee;



@UiController("referencebooks_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {

}