package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name = "REFERENCEBOOKS_EMPLOYEE", indexes = {
        @Index(name = "IDX_REFERENCEBOOKS_EMPLOYEE", columnList = "LAST_NAME, PERSONNEL_NUMBER, DEPARTMENT_ID")
})
@Entity(name = "referencebooks_Employee")
@NamePattern("%s|lastName")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -684329574851752601L;

    @NotNull
    @Column(name = "PERSONNEL_NUMBER", nullable = false, unique = true)
    private String personnelNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true)
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    private User user;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid format")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid format")
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid format")
    private String middleName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    @OnDelete(DeletePolicy.CASCADE)
    private Department department;

    @Column(name = "EMAIL", unique = true)
    @Email
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "Invalid format")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }
}