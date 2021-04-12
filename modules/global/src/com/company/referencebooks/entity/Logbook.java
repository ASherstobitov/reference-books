package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PublishEntityChangedEvents
@Table(name = "REFERENCEBOOKS_LOGBOOK", indexes = {
        @Index(name = "IDX_REFERENCEBOOKS_LOGBOOK", columnList = "CODE")
})
@Entity(name = "referencebooks_Logbook")
@NamePattern("%s|name")
public class Logbook extends StandardEntity {
    private static final long serialVersionUID = -7961839454860071249L;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull
    @Pattern(message = "Неверный формат", regexp = "^[\u0410-\u042F][\u0430-\u044F]{2} - ((MM\\.|DD\\.MM\\.)(YYYY|YY)|(YYYY|YY)) - \u2116$")
    @Column(name = "FORMAT", nullable = false)
    private String format;

    @Column(name = "AMOUNT_")
    private Long amount;

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
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