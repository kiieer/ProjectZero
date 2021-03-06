package com.revature.project.pctc.dao;

import java.util.List;

import com.revature.project.pctc.structures.Account;

public interface AccountDAO {
	//This interface will act as a contract for all of the Data Access Objects implementation classes.
	//CollectionDAO, FileDAO, PostgresDAO
	
	// Create
	boolean addAccount(int cId, Account a);
	
	// Read
	List<Account> getAccounts(int p);
	
	List<Account> getAccountById(int p);
	
	List<Account> accountFilter(int less, int more);
	
	// Update
	Account updateAccount(int p, Account a);
	
	boolean depositIntoAccount(int p, Account deposit);
	
	boolean withdrawFromAccount(int p, Account withdraw);
	
	// Delete
	boolean deleteAccount(int p);
}
