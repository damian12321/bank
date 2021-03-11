DROP DATABASE IF EXISTS spring_bank;
CREATE DATABASE spring_bank;
USE spring_bank;
CREATE TABLE `customers` (
`id` int NOT NULL AUTO_INCREMENT,
`first_name` varchar(30) default null,
`last_name` varchar(30) default null,
primary key (`id`)
);

CREATE TABLE `accounts` (
`id` int NOT NULL AUTO_INCREMENT,
`account_number` int default null,
`pin_number` varchar(64) default null,
`balance` FLOAT,
`customer_id` int default null,
primary key (`id`),
KEY `FK_CUSTOMER_ID_idx` (`customer_id`),
CONSTRAINT `FK_CUSTOMER` 
FOREIGN KEY (`customer_id`) 
REFERENCES `customers` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `transactions` (
`id` int NOT NULL AUTO_INCREMENT,
`transaction_type` varchar(30) default null,
`account_id` int default null,
`date` DATETIME default null,
primary key (`id`),
KEY `FK_account_ID_idx` (`account_id`),
CONSTRAINT `FK_ACCOUNT` 
FOREIGN KEY (`account_id`) 
REFERENCES `accounts` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `transfers` (
`id` int NOT NULL AUTO_INCREMENT,
`from_account` int not null,
`to_account` int not null,
primary key (`id`)
);