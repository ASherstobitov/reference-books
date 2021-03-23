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
);