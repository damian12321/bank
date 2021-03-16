DROP DATABASE IF EXISTS spring_bank;
CREATE DATABASE spring_bank;
USE spring_bank;
CREATE TABLE `accounts` (
`id` int NOT NULL AUTO_INCREMENT,
`account_number` int unique not null,
`pin_number` int not null,
`balance` FLOAT,
`login_attempts` int default '3',
`active` BOOL default false,
primary key (`id`)
);
CREATE TABLE `customers` (
`id` int NOT NULL AUTO_INCREMENT,
`first_name` varchar(30) not null,
`last_name` varchar(30) not null,
`account_id` int not null,
`password` varchar(30) not null,
primary key (`id`),
KEY `FK_ACCOUNTS_ID_idx` (`account_id`),
CONSTRAINT `FK_ACCOUNTID` 
FOREIGN KEY (`account_id`) 
REFERENCES `accounts` (`id`) 
);
CREATE TABLE `transactions` (
`id` int NOT NULL AUTO_INCREMENT,
`transaction_type` varchar(20) not null,
`amount` FLOAT not null,
`account_id` int default null,
`date` DATETIME not null,
`description` varchar(64) not null,
primary key (`id`),
KEY `FK_account_ID_idx` (`account_id`),
CONSTRAINT `FK_ACCOUNT` 
FOREIGN KEY (`account_id`) 
REFERENCES `accounts` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
);
SET FOREIGN_KEY_CHECKS = 1;
-- Populate some values 
INSERT INTO accounts VALUES(1,200,2000,200,3,1);
INSERT INTO accounts VALUES(2,201,2000,200,3,1);
INSERT INTO customers VALUES(1,"Damian","Juruś",1,"1234");
INSERT INTO customers VALUES(2,"Adrian","Juruś",2,"1234");