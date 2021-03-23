package com.company.referencebooks.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "REFERENCEBOOKS_COMPANY")
@Entity(name = "referencebooks_Company")
public class Company extends StandardEntity {
    private static final long serialVersionUID = 8511792459938552156L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "SHORT_NAME", nullable = false)
    private String shortName;

    @NotNull
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "LEGAL_ADDRESS")
    private String legalAddress;

    @Column(name = "POST_ADDRESS")
    private String postAddress;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "company")
    private OutgoingDocument outgoingDocument;

    public OutgoingDocument getOutgoingDocument() {
        return outgoingDocument;
    }

    public void setOutgoingDocument(OutgoingDocument outgoingDocument) {
        this.outgoingDocument = outgoingDocument;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}