package com.company.referencebooks.web.screens.department;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Department;

@UiController("referencebooks_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
@LoadDataBeforeShow
public class DepartmentEdit extends StandardEditor<Department> {
}