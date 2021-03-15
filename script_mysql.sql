DROP DATABASE IF EXISTS spring_bank;
CREATE DATABASE spring_bank;
USE spring_bank;
CREATE TABLE `customers` (
`id` int NOT NULL AUTO_INCREMENT,
`first_name` varchar(30) not null,
`last_name` varchar(30) not null,
primary key (`id`)
);

CREATE TABLE `accounts` (
`id` int NOT NULL AUTO_INCREMENT,
`account_number` int unique not null,
`pin_number` int not null,
`balance` FLOAT,
`customer_id` int not null,
`login_attempts` int default '3',
`active` boolean not null,
primary key (`id`),
KEY `FK_CUSTOMER_ID_idx` (`customer_id`),
CONSTRAINT `FK_CUSTOMER` 
FOREIGN KEY (`customer_id`) 
REFERENCES `customers` (`id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
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