alter table REFERENCEBOOKS_OUTGOING_DOCUMENT alter column RECIEPIENT rename to RECIEPIENT__U27805 ^
alter table REFERENCEBOOKS_OUTGOING_DOCUMENT alter column CONTENT rename to CONTENT__U93659 ^
alter table REFERENCEBOOKS_OUTGOING_DOCUMENT add column CONTEXT varchar(255) ;
alter table REFERENCEBOOKS_OUTGOING_DOCUMENT add column RECIPIENT varchar(255) ;
