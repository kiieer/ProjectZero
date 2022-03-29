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
	void getAccountTest() {
		List<Account> testList = dao.getAccounts(globalAccount.getcId());
		List<Account> emptyList = new ArrayList<Account>();
		Assertions.assertNotEquals(emptyList, testList);
	}
	
	@Test
	@Order(3)
	void getAccountsById() {
		 List<Account> accList = dao.getAccountById(globalAccount.getId());
		 Assertions.assertEquals(1, accList.size());
	}
	
}