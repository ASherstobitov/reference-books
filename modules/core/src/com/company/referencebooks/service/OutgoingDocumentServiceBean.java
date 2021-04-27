package com.company.referencebooks.service;

import com.company.referencebooks.entity.Employee;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@Service(OutgoingDocumentService.NAME)
public class OutgoingDocumentServiceBean implements OutgoingDocumentService {

    @Inject
    private DataManager dataManager;
/**
 *
 * Get an Employee by the user UUID
 * This method @return The Employee who user UUID equal searching UUID
 * */
    public Employee getEmployeeByUserUuid(UUID userUUID) {
      Optional<Employee> employee = dataManager.loadValue("select em from referencebooks_Employee em " +
                "where em.user.id = :userUUID", Employee.class).parameter("userUUID", userUUID).optional();
      return employee.orElse(null);
    }
}