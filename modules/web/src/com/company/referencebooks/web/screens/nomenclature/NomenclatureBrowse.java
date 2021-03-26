package com.company.referencebooks.web.screens.nomenclature;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Nomenclature;

@UiController("referencebooks_Nomenclature.browse")
@UiDescriptor("nomenclature-browse.xml")
@LookupComponent("nomenclaturesTable")
@LoadDataBeforeShow
public class NomenclatureBrowse extends StandardLookup<Nomenclature> {
}