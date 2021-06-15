## Table of contents
* [General info](#general-info)
* [Project assumptions](#project-assumptions)
* [Application idea](#application-idea)
* [Technologies](#technologies)
* [Setup](#setup)
* [List of endpoints](#list-of-endpoints)
* [List of objects](#list-of-objects)
* [Screenshots](#screenshots)

## General info
This project is a RESTful bank application.
	
## Project assumptions
* Use SQL Database to storage the data
* Use Spring Security
* Use Spring AOP
* Use Hibernate
* Use JUnit 5

## Application idea
* Every customer can create the account 
* Every customer can get all information about their own account
* Every customer can perform banking operations such as deposit money, withdraw money, check balance, transfer between accounts.
* Only the administrator can get information about accounts, customers, latest transactions.
* Block third party activities on the account.

## Technologies
Project is created with:
* Java 8
* Spring
* Hibernate
* JUnit 5
* Maven 3
* MySQL

## Setup
Clone the repo from github:

```
$ git clone https://github.com/damian12321/bank
```

You can run the application on your favourite IDE using an application server such as Tomcat.
The app will start running at http://localhost:8080.
You can also create a .war file 

```
$ mvn clean package
```

and deploy it directly to the application server.
You can test the application by Postman 
https://www.postman.com/
or use my own bank client application
https://github.com/damian12321/bank-client

## List of endpoints

IN PROGRESS

## List of objects
List of objects saved as JSON 

```
Customer
{
"id": int,
"firstName": "String",
"lastName": "String",
"account": Account object,
"password": "String"
}

Account
{
"id": int,
"accountNumber": int,
"pinNumber": int,
"balance": float,
"loginAttempts": int,
"isActive": boolean,
"transactionList": [Transaction objects]
}

Transaction
{
"id": int,
"transactionType": "String",
"amount": float,
"date": "Date object",
"description": "String"
}
```
## Screenshots
#### Example result of GET /api/account 
![Screen1](./img/Screen1.png)

#### Entity Relationship Diagram
![Screen2](./img/Screen2.png)
