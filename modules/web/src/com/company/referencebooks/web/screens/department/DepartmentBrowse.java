package com.company.referencebooks.web.screens.department;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Department;

@UiController("referencebooks_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
@LoadDataBeforeShow
public class DepartmentBrowse extends StandardLookup<Department> {
}