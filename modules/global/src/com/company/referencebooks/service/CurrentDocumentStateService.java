package com.company.referencebooks.service;

import com.company.referencebooks.entity.State;

public interface CurrentDocumentStateService {
    String NAME = "referencebooks_CurrentDocumentStateService";

    State setCurrentState(String procTaskName);
}