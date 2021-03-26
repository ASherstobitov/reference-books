package com.company.referencebooks.service;

import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(LogbookService.NAME)
public class LogbookServiceBean implements LogbookService {

    @Inject
    protected UniqueNumbersAPI uniqueNumbersAPI;

    public long getNextValue() {
        return uniqueNumbersAPI.getNextNumber("code");
    }

}