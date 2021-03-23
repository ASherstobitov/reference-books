package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "REFERENCEBOOKS_OUTGOING_DOCUMENT")
@Entity(name = "referencebooks_OutgoingDocument")
@NamePattern("%s|name")
public class OutgoingDocument extends StandardEntity {
    private static final long serialVersionUID = -4174784876363275126L;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_DOCUMENT_ID")
    private TypeDocument typeDocument;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EXECUTOR_ID")
    private Employee executor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SIGNER_ID")
    private Employee signer;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "CONDITION_")
    private String condition;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_ID")
    private Employee author;

    @NotNull
    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CHANGING_DATE")
    private String changingDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGBOOK_ID")
    private Logbook logbook;

    @Column(name = "CONTENT")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOMENCLATURE_CASE_ID")
    private Nomenclature nomenclatureCase;

    @Temporal(TemporalType.DATE)
    @Column(name = "SENT_WORK")
    private Date sentWork;

    public Date getSentWork() {
        return sentWork;
    }

    public void setSentWork(Date sentWork) {
        this.sentWork = sentWork;
    }

    public Nomenclature getNomenclatureCase() {
        return nomenclatureCase;
    }

    public void setNomenclatureCase(Nomenclature nomenclatureCase) {
        this.nomenclatureCase = nomenclatureCase;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
    }

    public void setCondition(Condition condition) {
        this.condition = condition == null ? null : condition.getId();
    }

    public String getCondition() {
        return condition == null ? null : Condition.fromId(condition);
    }

    public String getChangingDate() {
        return changingDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Employee getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public Employee getSigner() {
        return signer;
    }

    public void setSigner(Employee signer) {
        this.signer = signer;
    }

    public Employee getExecutor() {
        return executor;
    }

    public void setExecutor(Employee executor) {
        this.executor = executor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }
}