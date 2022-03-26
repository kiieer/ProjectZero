package com.revature.project.pctc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project.pctc.structures.Account;
import com.revature.project.pctc.utilities.ConnUtil;

public class AccountPostgresDAO implements AccountDAO {

	@Override
	public List<Account> getAccounts(int p) {
		// TODO Auto-generated method stub
		String selectAccounts = "select * from account where client_id=?";
		ArrayList<Account> aList = new ArrayList<Account>();
		Account a;
		try (Connection conn = ConnUtil.createConnection();){
		PreparedStatement ptsmt = conn.prepareStatement(selectAccounts);
		ptsmt.setInt(1, p);
		ResultSet rs = ptsmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String accNum = rs.getString("account_number");
			int bal = rs.getInt("account_balance");
			int cId = rs.getInt("client_id");
			a = new Account(id, accNum, bal, cId);
			aList.add(a);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}

	@Override
	public boolean addAccount(int cId, Account a) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnUtil.createConnection();){
		PreparedStatement ptsmt = conn.prepareStatement("INSERT into account values(?,?,?,?)");
		ptsmt.setInt(1, a.getId());
		ptsmt.setString(2, a.getAccNum());
		ptsmt.setInt(3, a.getBal());
		ptsmt.setInt(4, cId);
		ptsmt.execute();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Account> getAccountById(int p) {
		// TODO Auto-generated method stub
		ArrayList<Account> aList = new ArrayList<Account>();
		Account a;
		try (Connection conn = ConnUtil.createConnection();){
		PreparedStatement ptsmt = conn.prepareStatement("select * from account where id=?");
		ptsmt.setInt(1, p);
		ResultSet rs = ptsmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String accNum = rs.getString("account_number");
			int bal = rs.getInt("account_balance");
			int cId = rs.getInt("client_id");
			a = new Account(id, accNum, bal, cId);
			aList.add(a);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}

	@Override
	public boolean updateAccount(int p, Account a) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnUtil.createConnection();){
		PreparedStatement ptsmt = conn.prepareStatement("update account set account_number = ? where id = ?");
		ptsmt.setString(1, a.getAccNum());
		ptsmt.setInt(2, p);
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