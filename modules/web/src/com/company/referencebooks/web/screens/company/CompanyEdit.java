package com.company.referencebooks.web.screens.company;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Company;

@UiController("referencebooks_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
@LoadDataBeforeShow
public class CompanyEdit extends StandardEditor<Company> {
}