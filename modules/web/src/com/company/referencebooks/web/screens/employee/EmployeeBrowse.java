package com.company.referencebooks.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Employee;

@UiController("referencebooks_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}