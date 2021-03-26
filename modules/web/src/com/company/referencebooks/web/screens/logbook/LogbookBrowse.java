package com.company.referencebooks.web.screens.logbook;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Logbook;

@UiController("referencebooks_Logbook.browse")
@UiDescriptor("logbook-browse.xml")
@LookupComponent("logbooksTable")
@LoadDataBeforeShow
public class LogbookBrowse extends StandardLookup<Logbook> {
}