package com.revature.project.pctc.app;

import com.revature.project.pctc.handlers.AccountController;
import com.revature.project.pctc.handlers.ClientController;

import io.javalin.Javalin;

public class Main {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		// This adds a client in the database.
		// http://localhost:8080/clients
		app.post("/clients", ClientController.addClient);

		// This retrieves the full list of clients in the database.
		// http://localhost:8080/clients
		app.get("/clients", ClientController.getClients);

		// This retrieve a client by their id in the database.
		// returns a 404 when there is not a valid client.
		// http://localhost:8080/clients/1
		app.get("/clients/{id}", ClientController.getClientById);

		// Get all of the accounts for a specific client.
		// returns a 404 if the client does not exist.
		// http://localhost:8080/clients/1/accounts
		app.get("/clients/{id}/accounts", AccountController.getAccounts);

		// Adds an account for a specific client.
		// http://localhost:8080/clients/1/accounts
		app.post("/clients/{id}/accounts", AccountController.addAccount);

		// Get account by id.
		// Returns 404 if the account isn't valid.
		// http://localhost:8080/accounts/1
		app.get("/accounts/{id}", AccountController.getAccountById);

		// "Greater than, less than": filter through account balances through two
		// parameters.
		app.get("/accounts", AccountController.accountFilter);

		// This updates a specific client based on their id in the database.
		// Returns 404 if no client exists.
		// http://localhost:8080/clients/1
		app.put("/clients/{id}", ClientController.updateClient);

		// This deletes a specific client based on their id in the database.
		// Returns 404 if no client exists.
		app.delete("/clients/{id}", ClientController.deleteClient);

		// This updates a specific account based on their id in the database.
		// http://localhost:8080/accounts/1
		app.put("/accounts/{id}", AccountController.updateAccount);

		// This deletes a specific account based on their id in the database.
		// http://localhost:8080/accounts/1
		app.delete("/accounts/{id}", AccountController.deleteAccount);

		// Deposit an amount into an existing account in our database.
		// http://localhost:8080/accounts/1/deposit
		app.patch("/accounts/{id}/deposit", AccountController.depositIntoAccount);

		// Withdraw an amount from an existing account in our database.
		// http://localhost:8080/accounts/1/withdraw
		app.patch("/accounts/{id}/withdraw", AccountController.withdrawFromAccount);

	}
}
