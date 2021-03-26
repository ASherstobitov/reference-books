package com.company.referencebooks.web.screens.typedocument;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.TypeDocument;

@UiController("referencebooks_TyprDocument.edit")
@UiDescriptor("type-document-edit.xml")
@EditedEntityContainer("typeDocumentDc")
@LoadDataBeforeShow
public class TypeDocumentEdit extends StandardEditor<TypeDocument> {
}