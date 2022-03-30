# ProjectZero
Project Zero for Revature.
I successfully completed every single endpoint and functionality. I received a Green.

# Banking Application API
This was DUE Wednesday 03/30/2022

## Description

   The Banking App API is a Server-side application that facilitates the management of Client's Bank Accounts. A client create new accounts of various categories. Clients can deposit or withdraw funds from the account as well as close out accounts.
	
## Purpose

   We want to see that you can meet deadlines and that you can code. You are expected to complete the following requirements and give a 5 minute presentation of your project to our QC team.

## Requirements
1. All endpoints listed below must have a Postman request verifying functionality
2. Data is stored in a database.
3. Data Access is performed through the use of Data Access Objects.
4. All DAO methods must have a  test proving that they work
5. Application code should be on a git lab repo 

## RESTful Endpoints:

POST /clients => Creates a new client
	return a 201 status code

GET /clients => gets all clients
	return 200

GET /clients/10 => get client with id of 10
	return 404 if no such client exist

PUT /clients/12 => updates client with id of 12	
	return 404 if no such client exist

DELETE /clients/15 => deletes client with the id of 15
	return 404 if no such client exist
	return 205 if success

POST /clients/5/accounts =>creates a new account for client with the id of 5
	return a 201 status code

GET /clients/7/accounts => get all accounts for client 7
	return 404 if no client exists

(Optional)
GET /accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for between 400 and 200

GET /accounts/4 => get account with id 4 
	return 404 if no account or client exists

PUT /accounts/3 => update account with the id 3
	return 404 if no account or client exists

DELETE /accounts/6 => delete account 6 
	return 404 if no account or client exists


PATCH /accounts/12/deposit => deposit given amount (Body {"amount":500} )
	return 404 if no account exists

PATCH /accounts/12/withdraw => deposit given amount (Body {"amount":500} )
	return 404 if no account exists
	return 422 if insufficient funds

# Presentation
- You will be presenting before me and a member of QC.
- Your should aim to make your presentation about 5 minutes long.
- QC might ask a question or two about your project
- You will run your postman tests to show that your endpoints work
