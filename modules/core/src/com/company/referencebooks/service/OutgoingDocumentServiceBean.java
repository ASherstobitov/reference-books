package com.company.referencebooks.service;

import com.company.referencebooks.entity.Employee;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@Service(OutgoingDocumentService.NAME)
public class OutgoingDocumentServiceBean implements OutgoingDocumentService {

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Inject
    private DataManager dataManager;

    public long getNextValue() {
        return uniqueNumbersAPI.getNextNumber("registrationNumber");
    }

    public Employee getEmployeeByUserUuid(UUID userUUID) {

      Optional <Employee> employee = dataManager.loadValue("select em from referencebooks_Employee em " +
                "where em.user.id = :userUUID", Employee.class).parameter("userUUID", userUUID).optional();
      return employee.orElse(null);
    }

}