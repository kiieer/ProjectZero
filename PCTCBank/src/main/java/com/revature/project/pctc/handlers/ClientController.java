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
			ctx.result("[SUCCESSFUL] Your client has been added!");
			ctx.status(201);
		} else {
			ctx.result("[ERROR] There is an internal error.");
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
			ctx.result("[ERROR] We're sorry, the client you have specified does not exist in our database. Try again.");
			ctx.status(404);
			
		} else {
			ctx.json(cList);
			ctx.status(200);
		}
	};
	
	//This retrieves updates the name of a client with a specific ID in my database.
	public static Handler updateClient = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Client client = ctx.bodyAsClass(Client.class);
		List <Client> cList = dao.getClientById(p);
		if (cList.size() == 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the client you have specified does not exist in our database. Try again.");
		} else {
			client = dao.updateClient(client, p);
			ctx.status(200);
			ctx.result("[SUCCESS] The client has been successfully updated.");
		}
	};
	
	//This deletes a client in my database.
	public static Handler deleteClient = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		List <Client> cList = dao.getClientById(p);
		if (cList.size() == 0) {
			ctx.status(404);
			ctx.result("[ERROR] We're sorry, the client you have specified does not exist in our database. Try again.");
		} else if (dao.deleteClient(p)) {
				ctx.status(205);
				ctx.result("[SUCCESS] The client has been successfully deleted.");

			} else {
				ctx.status(404);
				ctx.result("ERROR: Internal error.");
			}
	};
	
}

