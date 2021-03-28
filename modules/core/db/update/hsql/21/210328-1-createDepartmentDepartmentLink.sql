create table REFERENCEBOOKS_DEPARTMENT_DEPARTMENT_LINK (
    DEPARTMENT_1_ID varchar(36) not null,
    DEPARTMENT_2_ID varchar(36) not null,
    primary key (DEPARTMENT_1_ID, DEPARTMENT_2_ID)
);
