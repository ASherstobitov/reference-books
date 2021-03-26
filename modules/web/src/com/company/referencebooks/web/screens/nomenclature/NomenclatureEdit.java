package com.company.referencebooks.web.screens.nomenclature;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.Nomenclature;

@UiController("referencebooks_Nomenclature.edit")
@UiDescriptor("nomenclature-edit.xml")
@EditedEntityContainer("nomenclatureDc")
@LoadDataBeforeShow
public class NomenclatureEdit extends StandardEditor<Nomenclature> {
}