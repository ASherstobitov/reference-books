package com.company.referencebooks.web.screens.outgoingdocument;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.OutgoingDocument;

@UiController("referencebooks_OutgoingDocument.browse")
@UiDescriptor("outgoing-document-browse.xml")
@LookupComponent("outgoingDocumentsTable")
@LoadDataBeforeShow
public class OutgoingDocumentBrowse extends StandardLookup<OutgoingDocument> {
}