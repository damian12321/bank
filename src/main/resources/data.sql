INSERT INTO account VALUES(1,200,2000,'damianjurus@wp.pl','Damian',1,'Juruś',6,'1234',1234);
INSERT INTO account VALUES(2,201,2000,'random@gmail.com','FirstName2',1,'LastName2',6,'1234',1234);
INSERT INTO TRANSACTION VALUES(1,500.0,sysdate(),'ANY TEXT. Send to account 201','OUTGOING_TRANSFER',1);
INSERT INTO TRANSACTION VALUES(2,500.0,sysdate(),'ANY TEXT. Send from account 201','INCOMING_TRANSFER',2);