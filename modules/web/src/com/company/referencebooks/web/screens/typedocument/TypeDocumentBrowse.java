package com.company.referencebooks.web.screens.typedocument;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.TypeDocument;

@UiController("referencebooks_TyprDocument.browse")
@UiDescriptor("type-document-browse.xml")
@LookupComponent("typeDocumentsTable")
@LoadDataBeforeShow
public class TypeDocumentBrowse extends StandardLookup<TypeDocument> {
}