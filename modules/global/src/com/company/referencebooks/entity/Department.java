package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table(name = "REFERENCEBOOKS_DEPARTMENT")
@Entity(name = "referencebooks_Department")
@NamePattern("%s|name")
public class Department extends StandardEntity {
    private static final long serialVersionUID = -6629845134260151789L;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @JoinColumn(name = "LEAD_DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    private Department leadDepartment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setLeadDepartment(Department leadDepartment) {
        this.leadDepartment = leadDepartment;
    }

    public Department getLeadDepartment() {
        return leadDepartment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        if (!super.equals(o)) return false;
        Department that = (Department) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, name, manager);
    }
}