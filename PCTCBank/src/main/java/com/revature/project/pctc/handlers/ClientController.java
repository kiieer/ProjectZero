package com.revature.project.pctc.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.project.pctc.structures.Client;
import com.revature.project.pctc.utilities.ConnUtil;

import io.javalin.http.Handler;

public class ClientController {
	
	public static Handler addClient = ctx -> {
		Client client = ctx.bodyAsClass(Client.class);
		Connection conn = ConnUtil.createConnection();
		PreparedStatement ptsmt = conn.prepareStatement("insert into client values(?, ?)");
		ptsmt.setInt(1, client.getId());
		ptsmt.setString(2, client.getName());
		ptsmt.execute();
		ctx.status(201);
		ptsmt.close();
	};
	
	//This retrieves all of the clients in my database.
	public static Handler getClients = ctx -> {
		Connection conn = ConnUtil.createConnection();
		String selectClients = "select * from client";
		PreparedStatement ptsmt = conn.prepareStatement(selectClients);
		ResultSet rs = ptsmt.executeQuery();
		ArrayList<Client> cList = new ArrayList<Client>();
		Client c;
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("full_name");
			c = new Client(id, name);
			cList.add(c);
		}

		ctx.json(cList);
		rs.close();
		ptsmt.close();
	};
	
	//This retrieves the client with a specific ID in my database.
	public static Handler getClientById = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnUtil.createConnection();
		String selectClients = "select * from client where id=?";
		PreparedStatement ptsmt = conn.prepareStatement(selectClients);
		ptsmt.setInt(1, p);
		ResultSet rs = ptsmt.executeQuery();
		ArrayList<Client> cList = new ArrayList<Client>();
		Client c;
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("full_name");
			c = new Client(id, name);
			cList.add(c);
		}
		
		if(cList.size() == 0) {
			ctx.status(404);
			
		}

		ctx.json(cList);
		rs.close();
		ptsmt.close();
	};
	
	//This retrieves updates the name of a client with a specific ID in my database.
	public static Handler updateClient = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Client c = ctx.bodyAsClass(Client.class);
		Connection conn = ConnUtil.createConnection();
		PreparedStatement ptsmt = conn.prepareStatement("update client set full_name = ? where id = ?");
		ptsmt.setString(1, c.getName());
		ptsmt.setInt(2, p);
		ptsmt.execute();
		ctx.status(200);
		ptsmt.close();
	};
	
	public static Handler deleteClient = ctx ->{
		int p = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnUtil.createConnection();
		PreparedStatement ptsmt = conn.prepareStatement("delete from client where id = ?");
		ptsmt.setInt(1, p);
		ptsmt.execute();
		ctx.status(205);
		ptsmt.close();	
	};
	
}

