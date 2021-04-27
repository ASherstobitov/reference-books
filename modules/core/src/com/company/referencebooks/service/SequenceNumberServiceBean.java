package com.company.referencebooks.service;

import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(SequenceNumberService.NAME)
public class SequenceNumberServiceBean implements SequenceNumberService {

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    /**
     * Get the next number
     * @retunt The next number of a sequence
     * */
    @Override
    public Long getNextNumber(String domain) {
        return uniqueNumbersAPI.getNextNumber(domain);
    }

    /**
     * Set a current number
     * Set the current number of a number's sequence
     * */
    @Override
    public void setCurrentNumber(String domain, long value) {
        uniqueNumbersAPI.setCurrentNumber(domain, value);
    }
}