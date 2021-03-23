package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "REFERENCEBOOKS_LOGBOOK")
@Entity(name = "referencebooks_Logbook")
@NamePattern("%s|name")
public class Logbook extends StandardEntity {
    private static final long serialVersionUID = -7961839454860071249L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "FORMAT", nullable = false, unique = true)
    private String format;

    @Column(name = "COUNT_")
    private String amount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "logbook")
    private OutgoingDocument outgoingDocument;

    public OutgoingDocument getOutgoingDocument() {
        return outgoingDocument;
    }

    public void setOutgoingDocument(OutgoingDocument outgoingDocument) {
        this.outgoingDocument = outgoingDocument;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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