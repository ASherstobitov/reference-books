create table REFERENCEBOOKS_OUTGOING_DOCUMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TYPE_DOCUMENT_ID varchar(36) not null,
    REGISTRATION_NUMBER varchar(255),
    REGISTRATION_DATE date,
    COMPANY_ID varchar(36) not null,
    EXECUTOR_ID varchar(36) not null,
    SIGNER_ID varchar(36),
    NOTES varchar(255),
    CONDITION_ varchar(255),
    NAME varchar(255) not null,
    AUTHOR_ID varchar(36) not null,
    CREATION_DATE varchar(255) not null,
    CHANGING_DATE varchar(255),
    LOGBOOK_ID varchar(36),
    CONTENT varchar(255),
    NOMENCLATURE_CASE_ID varchar(36),
    SENT_WORK date,
    --
    primary key (ID)
);