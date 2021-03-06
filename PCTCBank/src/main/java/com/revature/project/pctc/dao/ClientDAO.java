package com.revature.project.pctc.dao;

import java.util.List;

import com.revature.project.pctc.structures.Client;

public interface ClientDAO {
	//This interface will act as a contract for all of the Data Access Objects implementation classes.
	// CollectionDAO, FileDAO, PostgresDAO
	
	// CREATE 
	boolean addClient(Client client);
	
	// READ
	List<Client> getClients();
	
	List<Client> getClientById(int p);
	
	// UPDATE
	Client updateClient(Client client, int p);
	
	// DELETE
	boolean deleteClient(int p);
}
