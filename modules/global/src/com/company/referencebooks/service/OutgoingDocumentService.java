package com.company.referencebooks.service;

import com.company.referencebooks.entity.Employee;

import java.util.UUID;

public interface OutgoingDocumentService {
    String NAME = "referencebooks_OutgoingDocumentService";

    Employee getEmployeeByUserUuid(UUID userUUID);
}