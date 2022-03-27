package com.revature.project.pctc.handlers;

import java.util.List;

import com.revature.project.pctc.dao.ClientDAO;
import com.revature.project.pctc.dao.ClientPostgresDAO;
import com.revature.project.pctc.structures.Client;

import io.javalin.http.Handler;

public class ClientController {
	static ClientDAO dao = new ClientPostgresDAO();
	
	//Add a client to the database.
	public static Handler addClient = ctx -> {
		Client client = ctx.bodyAsClass(Client.class);
		
		if (dao.addClient(client)) {
			ctx.status(201);
		} else {
			ctx.status(404);
		}
	};
	
	//This retrieves all of the clients in my database.
	public static Handler getClients = ctx -> {
		List<Client>  cList = dao.getClients();
		ctx.json(cList);
	};
	
	//This retrieves the client with a specific ID in my database.
	public static Handler getClientById = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		List<Client>  cList = dao.getClientById(p);
		
		if(cList.size() == 0) {
			ctx.status(404);
			
		} else {
			ctx.json(cList);
			ctx.status(201);
		}
	};
	
	//This retrieves updates the name of a client with a specific ID in my database.
	//THIS DOESN'T WORK WHEN TRYING TO GET IT TO THROW THE 404 EXCEPTION.
	public static Handler updateClient = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Client client = ctx.bodyAsClass(Client.class);
		List <Client> cList = dao.getClientById(p);
		if (cList.size() == 0) {
			ctx.status(404);
		} else {
			Client updatedClient = dao.updateClient(client, p);
			ctx.status(200);
		}
	};
	
	//This deletes a client in my database.
	public static Handler deleteClient = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		if (dao.deleteClient(p)) {
			ctx.status(205);
		} else {
			ctx.status(404);
		}
	};
	
}

