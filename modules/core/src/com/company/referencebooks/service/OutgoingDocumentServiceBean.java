package com.company.referencebooks.service;

import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(OutgoingDocumentService.NAME)
public class OutgoingDocumentServiceBean implements OutgoingDocumentService {

    @Inject
    protected UniqueNumbersAPI uniqueNumbersAPI;

    public long getNextValue() {
        return uniqueNumbersAPI.getNextNumber("registrationNumber");
    }

}