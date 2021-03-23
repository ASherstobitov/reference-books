package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "REFERENCEBOOKS_NOMENCLATURE")
@Entity(name = "referencebooks_Nomenclature")
@NamePattern("%s|name")
public class Nomenclature extends StandardEntity {
    private static final long serialVersionUID = 788821067162315173L;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME")
    private String name;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "nomenclatureCase")
    private OutgoingDocument outgoingDocument;

    public OutgoingDocument getOutgoingDocument() {
        return outgoingDocument;
    }

    public void setOutgoingDocument(OutgoingDocument outgoingDocument) {
        this.outgoingDocument = outgoingDocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}