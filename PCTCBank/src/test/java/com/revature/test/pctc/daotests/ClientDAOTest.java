package com.revature.test.pctc.daotests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.project.pctc.dao.ClientDAO;
import com.revature.project.pctc.dao.ClientPostgresDAO;
import com.revature.project.pctc.structures.Client;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDAOTest {
	private static ClientDAO dao = new ClientPostgresDAO();
	private static Client globalClient;
	
	@BeforeAll
	// @AfterAll
	static void DBReset() {
		dao.deleteClient(101);
	}
	
	@Test
	@Order(1)
	void addClientTest() {
		Client c1 = new Client(101, "This Dude");
		dao.addClient(c1);
		ClientDAOTest.globalClient = c1;
		Assertions.assertEquals(101, globalClient.getId());
	}
	
	@Test
	@Order(2)
	void getClientTest() {
		List<Client> testList = new ArrayList<Client>();
		List<Client> emptyList = new ArrayList<Client>();
		testList = dao.getClients();
		Assertions.assertNotEquals(emptyList, testList);

	}
	
	@Test
	@Order(3)
	void getClientByIDTest() {
		Client c1 = new Client(101, "This Dude");
		List<Client> testList = dao.getClientById(c1.getId());
		Assertions.assertEquals(1, testList.size());
	}
	
	@Test
	@Order(4)
	void UpdateClientTest() {
		Client c1 = new Client(101, "Gordon Ramsey");
		c1 = dao.updateClient(c1, globalClient.getId());
		List<Client> clients = dao.getClientById(globalClient.getId());
		Assertions.assertEquals(c1.toString(), clients.get(0).toString());
		
	}
	
	@Test
	@Order(5)
	void DeleteClientTest() {
		Client c1 = new Client(101, "Gordon Ramsey");
		dao.deleteClient(c1.getId());
		Assertions.assertNotEquals(c1, globalClient);
		
	}
}
