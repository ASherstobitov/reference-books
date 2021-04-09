package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Table(name = "REFERENCEBOOKS_EMPLOYEE", indexes = {
        @Index(name = "IDX_REFERENCEBOOKS_EMPLOYEE", columnList = "LAST_NAME, PERSONNEL_NUMBER")
})
@Entity(name = "referencebooks_Employee")
@NamePattern("%s %s %s|lastName,firstName,middleName")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -684329574851752601L;

    @NotNull
    @Column(name = "PERSONNEL_NUMBER", nullable = false, unique = true)
    @Pattern(regexp = "\\d+", message = "Invalid format")
    private String personnelNumber;

    @OneToMany(mappedBy = "executor")
    private List<OutgoingDocument> outGoingDocuments;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @OnDelete(DeletePolicy.CASCADE)
    private User user;

    @NotNull
    @Pattern(message = "Invalid format", regexp = "[A-Z\u0410-\u042F][a-z\u0430-\u044F]*")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Pattern(message = "Invalid format", regexp = "[A-Z\u0410-\u042F][a-z\u0430-\u044F]*")
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    @Pattern(message = "Invalid format", regexp = "[A-Z\u0410-\u042F][a-z\u0430-\u044F]*")
    private String middleName;

    @JoinColumn(name = "DEPARTMENT_ID")
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    private Department department;

    @Column(name = "EMAIL", unique = true)
    @Email
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "Invalid format")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_FILE_ID")
    private FileDescriptor imageFile;

    public FileDescriptor getImageFile() {
        return imageFile;
    }

    public void setImageFile(FileDescriptor imageFile) {
        this.imageFile = imageFile;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<OutgoingDocument> getOutGoingDocuments() {
        return outGoingDocuments;
    }

    public void setOutGoingDocuments(List<OutgoingDocument> outGoingDocuments) {
        this.outGoingDocuments = outGoingDocuments;
    }

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

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }
}