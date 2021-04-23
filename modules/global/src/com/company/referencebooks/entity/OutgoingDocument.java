package com.company.referencebooks.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "REFERENCEBOOKS_OUTGOING_DOCUMENT")
@Entity(name = "referencebooks_OutgoingDocument")
@NamePattern("%s %s|typeDocument,name")
public class OutgoingDocument extends StandardEntity {
    private static final long serialVersionUID = -4174784876363275126L;

    @JoinColumn(name = "TYPE_DOCUMENT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    private TypeDocument typeDocument;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    @JoinTable(name = "REFERENCEBOOKS_OUTGOING_DOCUMENT_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "OUTGOING_DOCUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    private List<FileDescriptor> documents;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPANY_ID")
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    private Company company;

    @Column(name = "RECIPIENT")
    private String recipient;

    @Column(name = "TOPIC", nullable = false)
    @NotNull
    private String topic;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EXECUTOR_ID")
    private Employee executor;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    @JoinColumn(name = "SIGNER_ID")
    private Employee signer;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COORDINATOR_ID")
    private Employee coordinator;

    @Column(name = "NOTES")
    private String notes;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @JoinColumn(name = "AUTHOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column(name = "CREATION_DATE", nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "CHANGING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changingDate;

    @Column(name = "STATE")
    private String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGBOOK_ID", unique = true)
    private Logbook logbook;

    @Column(name = "CONTENT")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOMENCLATURE_CASE_ID")
    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    private Nomenclature nomenclatureCase;

    @Column(name = "SENT_WORK_DATE")
    @Temporal(TemporalType.DATE)
    private Date sentWorkDate;

    public Employee getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Employee coordinator) {
        this.coordinator = coordinator;
    }

    public State getState() {
        return state == null ? null : State.fromId(state);
    }

    public void setState(State state) {
        this.state = state == null ? null : state.getId();
    }

    public void setDocuments(List<FileDescriptor> documents) {
        this.documents = documents;
    }

    public List<FileDescriptor> getDocuments() {
        return documents;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setChangingDate(Date changingDate) {
        this.changingDate = changingDate;
    }

    public Date getChangingDate() {
        return changingDate;
    }

    public void setSentWorkDate(Date sentWorkDate) {
        this.sentWorkDate = sentWorkDate;
    }

    public Date getSentWorkDate() {
        return sentWorkDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Employee getExecutor() {
        return executor;
    }

    public void setExecutor(Employee executor) {
        this.executor = executor;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nomenclature getNomenclatureCase() {
        return nomenclatureCase;
    }

    public void setNomenclatureCase(Nomenclature nomenclatureCase) {
        this.nomenclatureCase = nomenclatureCase;
    }

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
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

}