package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "REFERENCEBOOKS_TYPR_DOCUMENT")
@Entity(name = "referencebooks_TyprDocument")
@NamePattern("%s|name")
public class TypeDocument extends StandardEntity {
    private static final long serialVersionUID = -8974112552649702874L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

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