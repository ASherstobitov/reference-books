alter table REFERENCEBOOKS_OUTGOING_DOCUMENT add constraint FK_REFERENCEBOOKS_OUTGOING_DOCUMENT_ON_EMPLOYEE foreign key (EMPLOYEE_ID) references REFERENCEBOOKS_EMPLOYEE(ID);
create index IDX_REFERENCEBOOKS_OUTGOING_DOCUMENT_ON_EMPLOYEE on REFERENCEBOOKS_OUTGOING_DOCUMENT (EMPLOYEE_ID);
