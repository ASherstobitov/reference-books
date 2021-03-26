package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@PublishEntityChangedEvents
@Table(name = "REFERENCEBOOKS_LOGBOOK", indexes = {
        @Index(name = "IDX_REFERENCEBOOKS_LOGBOOK", columnList = "CODE")
})
@Entity(name = "referencebooks_Logbook")
@NamePattern("%s|code")
public class Logbook extends StandardEntity {
    private static final long serialVersionUID = -7961839454860071249L;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "FORMAT", nullable = false, unique = true)
    private String format;

    @Column(name = "COUNT_")
    private String amount;

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