package com.company.referencebooks.web.screens.company;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Company;

@UiController("referencebooks_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
@LoadDataBeforeShow
public class CompanyBrowse extends StandardLookup<Company> {
}