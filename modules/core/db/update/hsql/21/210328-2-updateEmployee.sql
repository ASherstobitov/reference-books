alter table REFERENCEBOOKS_EMPLOYEE alter column USER_ID rename to USER_ID__U04492 ^
drop index IDX_REFERENCEBOOKS_EMPLOYEE_ON_USER ;
alter table REFERENCEBOOKS_EMPLOYEE add column USER_ID varchar(36) ;
