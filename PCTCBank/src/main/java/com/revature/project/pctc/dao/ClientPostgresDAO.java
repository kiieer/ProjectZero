package com.revature.project.pctc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project.pctc.structures.Client;
import com.revature.project.pctc.utilities.ConnUtil;

public class ClientPostgresDAO implements ClientDAO {

	@Override
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		String selectClients = "select * from client";
		ArrayList<Client> cList = new ArrayList<Client>();
		Client c;
		try (Connection conn = ConnUtil.createConnection();) {
		PreparedStatement ptsmt = conn.prepareStatement(selectClients);
		ResultSet rs = ptsmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("full_name");
			c = new Client(id, name);
			cList.add(c);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cList;
	}

	@Override
	public List<Client> getClientById(int p) {
		// TODO Auto-generated method stub
		String selectClients = "select * from client where id=?";
		ArrayList<Client> cList = new ArrayList<Client>();
		Client c;
		try (Connection conn = ConnUtil.createConnection();) {
		PreparedStatement ptsmt = conn.prepareStatement(selectClients);
		ptsmt.setInt(1, p);
		ResultSet rs = ptsmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("full_name");
			c = new Client(id, name);
			cList.add(c);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cList;
	}

	
	@Override
	public boolean addClient(Client client) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnUtil.createConnection();) {
			PreparedStatement ptsmt = conn.prepareStatement("insert into client values(?, ?)");
			ptsmt.setInt(1, client.getId());
			ptsmt.setString(2, client.getName());
			ptsmt.execute();
			ptsmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Client updateClient(Client client, int p) {
		// TODO Auto-generated method stub
		Connection conn = ConnUtil.createConnection();
		try {
			PreparedStatement ptsmt = conn.prepareStatement("update client set full_name = ? where id = ?");
			ptsmt.setString(1, client.getName());
			ptsmt.setInt(2, p);
			ptsmt.execute();
			ptsmt.close();
			return client;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return client;
		}
	}

	@Override
	public boolean deleteClient(int p) {
		// TODO Auto-generated method stub
		Connection conn = ConnUtil.createConnection();
		PreparedStatement ptsmt;
		try {
			ptsmt = conn.prepareStatement("delete from client where id = ?");
			ptsmt.setInt(1, p);
			ptsmt.execute();
			ptsmt.close();	
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
