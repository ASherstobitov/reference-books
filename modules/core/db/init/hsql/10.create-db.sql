-- begin REFERENCEBOOKS_DEPARTMENT
create table REFERENCEBOOKS_DEPARTMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255) not null,
    NAME varchar(255) not null,
    MANAGER_ID varchar(36),
    LEAD_DEPARTMENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_DEPARTMENT
-- begin REFERENCEBOOKS_EMPLOYEE
create table REFERENCEBOOKS_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PERSONNEL_NUMBER varchar(255) not null,
    USER_ID varchar(36),
    LAST_NAME varchar(255) not null,
    FIRST_NAME varchar(255) not null,
    MIDDLE_NAME varchar(255),
    DEPARTMENT_ID varchar(36),
    EMAIL varchar(255),
    PHONE_NUMBER varchar(255),
    IMAGE_FILE_ID varchar(36),
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_EMPLOYEE
-- begin REFERENCEBOOKS_COMPANY
create table REFERENCEBOOKS_COMPANY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255) not null,
    SHORT_NAME varchar(255) not null,
    FULL_NAME varchar(255) not null,
    LEGAL_ADDRESS varchar(255),
    POST_ADDRESS varchar(255),
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_COMPANY
-- begin REFERENCEBOOKS_OUTGOING_DOCUMENT
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
    TYPE_DOCUMENT_ID varchar(36),
    REGISTRATION_NUMBER varchar(255),
    REGISTRATION_DATE date,
    COMPANY_ID varchar(36) not null,
    RECIPIENT varchar(255),
    TOPIC varchar(255) not null,
    EXECUTOR_ID varchar(36) not null,
    SIGNER_ID varchar(36) not null,
    COORDINATOR_ID varchar(36) not null,
    NOTES varchar(255),
    NAME varchar(255) not null,
    AUTHOR_ID varchar(36),
    CREATION_DATE timestamp not null,
    CHANGING_DATE timestamp,
    STATE varchar(50),
    LOGBOOK_ID varchar(36),
    CONTENT varchar(255),
    NOMENCLATURE_CASE_ID varchar(36),
    SENT_WORK_DATE date,
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_OUTGOING_DOCUMENT
-- begin REFERENCEBOOKS_LOGBOOK
create table REFERENCEBOOKS_LOGBOOK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255),
    NAME varchar(255),
    FORMAT varchar(255) not null,
    AMOUNT_ bigint,
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_LOGBOOK
-- begin REFERENCEBOOKS_TYPR_DOCUMENT
create table REFERENCEBOOKS_TYPR_DOCUMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255) not null,
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_TYPR_DOCUMENT
-- begin REFERENCEBOOKS_NOMENCLATURE
create table REFERENCEBOOKS_NOMENCLATURE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255) not null,
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end REFERENCEBOOKS_NOMENCLATURE
-- begin REFERENCEBOOKS_OUTGOING_DOCUMENT_FILE_DESCRIPTOR_LINK
create table REFERENCEBOOKS_OUTGOING_DOCUMENT_FILE_DESCRIPTOR_LINK (
    OUTGOING_DOCUMENT_ID varchar(36) not null,
    FILE_DESCRIPTOR_ID varchar(36) not null,
    primary key (OUTGOING_DOCUMENT_ID, FILE_DESCRIPTOR_ID)
)^
-- end REFERENCEBOOKS_OUTGOING_DOCUMENT_FILE_DESCRIPTOR_LINK
