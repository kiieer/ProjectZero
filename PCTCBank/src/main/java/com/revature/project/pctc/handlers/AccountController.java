package com.revature.project.pctc.handlers;

import java.util.List;

import com.revature.project.pctc.dao.AccountDAO;
import com.revature.project.pctc.dao.AccountPostgresDAO;
import com.revature.project.pctc.structures.*;
import io.javalin.http.Handler;

public class AccountController {
	static AccountDAO dao = new AccountPostgresDAO();
	
	//Gets all accounts.
	public static Handler getAccounts = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		List<Account>  aList = dao.getAccounts(p);
		if(aList.size() == 0) {
			ctx.status(404);
			ctx.result("[ERROR] There is an internal error or the specified client does not exist in our database.");
		} else {
			ctx.json(aList);
			ctx.status(200);
		}
	};

	//Adds an account.
	public static Handler addAccount = ctx -> {
		int cId = Integer.parseInt(ctx.pathParam("id"));
		Account a = ctx.bodyAsClass(Account.class);
		if (dao.addAccount(cId, a)) {
			ctx.result("[SUCCESS] Your account has been added.");
			ctx.status(201);
		} else {
			ctx.result("[ERROR] There is an internal error or the specified client does not exist in our database.");
			ctx.status(404);
		}
	};
	 
	//Filter accounts by two balance variables.
	public static Handler accountFilter = ctx -> {
		int less = Integer.parseInt(ctx.queryParam("less"));
		int more = Integer.parseInt(ctx.queryParam("more"));
		List<Account>  aList = dao.accountFilter(less, more);
		if(aList.size() == 0) {
			ctx.result("[ERROR] There is an internal error or the specified client does not exist in our database.");
			ctx.status(404);
			
		} else {
			ctx.json(aList);
			ctx.status(200);
		}
	};
	
	//get account by ID
	public static Handler getAccountById = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		List<Account> aList = dao.getAccountById(p);
		if(aList.size() == 0) {
			ctx.result("[ERROR] There is an internal error or the specified account does not exist in our database.");
			ctx.status(404);
			
		} else {
		ctx.json(aList);
		}
	};
	
	//This updates the acc number of a bank account with a specific ID in my database.
	public static Handler updateAccount = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Account a = ctx.bodyAsClass(Account.class);
		List <Account> aList = dao.getAccountById(p);
		if (aList.size() == 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the account you have specified does not exist in our database. Try again.");
		} else {
			a = dao.updateAccount(p, a);
			ctx.status(200);
			ctx.result("[SUCCESS] The client has been successfully updated.");
		}
		
	};
	 
	//This deletes an account in my database.
	public static Handler deleteAccount = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		List <Account> aList = dao.getAccountById(p);
		if (aList.size()== 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the account you have specified does not exist in our database. Try again.");
		} else if (dao.deleteAccount(p)) {
			ctx.status(205);
			ctx.result("[SUCCESS] The account has been successfully deleted.");
		} else {
			ctx.status(404);
			ctx.result("[ERROR] There is an internal error.");
		}
	};
	
	// Deposit an amount into an existing account.
	public static Handler depositIntoAccount = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		Account deposit = ctx.bodyAsClass(Account.class);
		List <Account> aList = dao.getAccountById(p);
		
		if (aList.size()== 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the account you have specified does not exist in our database. Try again.");
		} else {
			if (dao.depositIntoAccount(p, deposit)) {
				ctx.status(200);
				ctx.result("[SUCCESS] The amount has been deposited into the account.");
			} else {
				ctx.status(404);
				ctx.result("[ERROR] There is an internal error.");
			}
		}
		
	};
	
	//Withdraw from an amount from an existing account.
	public static Handler withdrawFromAccount = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		Account withdraw = ctx.bodyAsClass(Account.class);
		List <Account> aList = dao.getAccountById(p);
		
		if (aList.size()== 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the account you have specified does not exist in our database. Try again.");
		} else {
			if (dao.withdrawFromAccount(p, withdraw)) {
				ctx.status(200);
				ctx.result("[SUCCESS] The amount has been withdrawn from the account.");
			} else {
				ctx.status(422);
				ctx.result("[ERROR] There is insufficient funds to withdraw.");
			}
		}
	};

}