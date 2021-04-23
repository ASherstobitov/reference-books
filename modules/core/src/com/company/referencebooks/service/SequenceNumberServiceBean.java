package com.company.referencebooks.service;

import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(SequenceNumberService.NAME)
public class SequenceNumberServiceBean implements SequenceNumberService {

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    public Long getNextNumber(String domain) {
        return uniqueNumbersAPI.getNextNumber(domain);
    }

    @Override
    public Long getCurrentNumber(String domain) {
        return uniqueNumbersAPI.getCurrentNumber(domain);
    }

    @Override
    public void deleteSeq(String domain) {
        uniqueNumbersAPI.deleteSequence(domain);
    }

    @Override
    public void setCurrentNumber(String domain, long value) {
        uniqueNumbersAPI.setCurrentNumber(domain, value);
    }
}