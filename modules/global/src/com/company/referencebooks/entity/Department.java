package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "MANAGER_ID")
    @Lookup(type = LookupType.DROPDOWN, actions = {})
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAD_DEPARTMENT_ID")
    @Lookup(type = LookupType.DROPDOWN, actions = {})
    private Department leadDepartment;

    @OneToMany(mappedBy = "leadDepartment")
    private List<Department> sonDeparts;

    public List<Department> getSonDeparts() {
        return sonDeparts;
    }

    public void setSonDeparts(List<Department> sonDeparts) {
        this.sonDeparts = sonDeparts;
    }

    public Department getLeadDepartment() {
        return leadDepartment;
    }

    public void setLeadDepartment(Department leadDepartment) {
        this.leadDepartment = leadDepartment;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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