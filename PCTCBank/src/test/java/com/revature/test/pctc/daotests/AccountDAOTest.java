package com.revature.test.pctc.daotests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.project.pctc.dao.AccountDAO;
import com.revature.project.pctc.dao.AccountPostgresDAO;
import com.revature.project.pctc.structures.Account;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDAOTest {
	private static AccountDAO dao = new AccountPostgresDAO();
	private static Account globalAccount;
	
	@BeforeAll
	// @AfterAll
	static void DBReset() {
		dao.deleteAccount(101);
	}
	
	@Test
	@Order(1)
	void addAccountTest() {
		 Account a1 = new Account(101, "T357", 1337, 46);
		 dao.addAccount(a1.getcId(), a1);
		 AccountDAOTest.globalAccount = a1;
		 Assertions.assertEquals("T357", globalAccount.getAccNum());
		 
	}
	
	@Test
	@Order(2)
	void sadAddAccountTest() {
		 Account a1 = new Account(1, "T357", 1337, 46);
		 boolean value = dao.addAccount(a1.getcId(), a1);
		 Assertions.assertEquals(false, value);
		 
	}
	
	@Test
	@Order(3)
	void getAccountTest() {
		List<Account> testList = dao.getAccounts(globalAccount.getcId());
		List<Account> emptyList = new ArrayList<Account>();
		Assertions.assertNotEquals(emptyList, testList);
	}
	
	@Test
	@Order(4)
	void getAccountsById() {
		 List<Account> accList = dao.getAccountById(globalAccount.getId());
		 Assertions.assertEquals(1, accList.size());
	}
	
	@Test
	@Order(5)
	void updateAccountTest() {
		 Account a1 = new Account(101, "R00T", 1337, 46);
		 a1 = dao.updateAccount(globalAccount.getId(), a1);
		 List<Account> accounts = dao.getAccountById(globalAccount.getId());
		Assertions.assertEquals(a1.toString(), accounts.get(0).toString());
	}
	
	@Test
	@Order(6)
	void GreaterThanLessThanTest() {
		List<Account> account = dao.accountFilter(300, 1);
		Assertions.assertEquals(1, account.size());
	}
	
	@Test
	@Order(7)
	void depositAccountTest() {
		Account a1 = new Account(100);
		boolean value = dao.depositIntoAccount(globalAccount.getId(), a1);
		Assertions.assertEquals(true, value);
		}
	
	@Test
	@Order(8)
	void withdrawAccountTest() {
		Account a1 = new Account(100);
		boolean value = dao.withdrawFromAccount(globalAccount.getId(), a1);
		Assertions.assertEquals(true, value);
		}
	
	@Test
	@Order(9)
	void deleteAccount() {
		Account a1 = new Account(101, "R00T", 1337, 46);
		dao.deleteAccount(a1.getId());
		Assertions.assertNotEquals(a1, globalAccount);
	}
	
}
