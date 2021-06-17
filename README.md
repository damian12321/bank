## Table of contents
* [General info](#general-info)
* [Application idea](#application-idea)
* [Technologies](#technologies)
* [Setup](#setup)
* [List of endpoints](#list-of-endpoints)
* [List of objects](#list-of-objects)
* [Screenshots](#screenshots)

## General info
This project is a RESTful bank application.
	
## Application idea
* Every client can create the account 
* Every client can get all information about their own account
* Every client can perform banking operations such as deposit money, withdraw money, check balance, transfer between accounts.
* Only the administrator can get information about accounts, latest transactions.
* Block third party activities on the account.

## Technologies
Project is created with:
* Java
* Spring Boot
* Hibernate
* JUnit
* Maven
* H2 Database

## Setup
Clone the repo from github:

```
$ git clone https://github.com/damian12321/bank
```

You can run the application on your favourite IDE or by command line in the application root folder.

```
$ mvn clean install
$ java -jar target/bank-0.0.1-SNAPSHOT.jar
```
The app will start running at http://localhost:8080/

You can test the application by Postman 
https://www.postman.com/
or use my own bank client application (in progress)
https://github.com/damian12321/bank-client

## List of endpoints
```
GET    /api/accounts 												- get all accounts, only for administrator
GET    /api/accounts/{accountId}/{password} 									- get information about account
GET    /api/accounts/{accountId}/{password}/transactions 							- get information about account transactions
GET    /api/transactions 											- get all transactions, only for administrator
GET    /api/transactions/{transactionId} 									- get transaction, only for administrator
GET    /api/accounts/number 											- get free account number
PUT    /api/accounts 												- update account
       Request Body: Account object
PUT    /api/transactions 											- update transaction, only for administrator
       Request Body: Transaction object
POST   /api/accounts 												- create account
       Request Body: Account object
POST   /api/deposit/{accountNumber}/{pinNumber}/{amount} 							- deposit money
       Path Variable: int accountNumber, int pinNumber, float amount
POST   /api/transfer/{fromAccountNumber}/{pinNumber}/{toAccountNumber}/{amount}/{message}   			- transfer money to other account 
       Path Variable: int fromAccountNumber, int pinNumber, int toAccountNumber, float amount, String message
POST   /api/withdraw/{accountNumber}/{pinNumber}/{amount}                                   			- withdraw money
       Path Variable: int accountNumber, int pinNumber, float amount
DELETE /api/accounts/{accountId}										- delete account, only for administrator
       Path Variable: int accountId
DELETE /api/transactions/{transactionId} 									- delete transaction, only for administrator
       Path Variable: int transactionId
```	   
More informations about endpoints and responses you can find in Swagger Documentation available at http://localhost:8080/swagger-ui/#/

## List of objects 
Saved as a JSON
```
Account
{
"id": int,
"firstName": "String",
"lastName": "String",
"account": Account object,
"password": "String"
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
#### Example result of GET /api/accounts
![Screen1](./img/Screen1.png)
#### Example result of GET /api/transactions
![Screen2](./img/Screen2.png)

