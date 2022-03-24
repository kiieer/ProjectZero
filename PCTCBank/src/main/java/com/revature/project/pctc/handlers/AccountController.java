package com.revature.project.pctc.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.project.pctc.structures.*;
import com.revature.project.pctc.utilities.ConnUtil;
import io.javalin.http.Handler;

public class AccountController {
	//Gets all accounts.
	public static Handler getAccounts = ctx -> {
		int p = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnUtil.createConnection();
		String selectAccounts = "select * from account where client_id=?";
		PreparedStatement ptsmt = conn.prepareStatement(selectAccounts);
		ptsmt.setInt(1, p);
		ResultSet rs = ptsmt.executeQuery();
		ArrayList<Account> aList = new ArrayList<Account>();
		Account a;
		while (rs.next()) {
			int id = rs.getInt("id");
			String accNum = rs.getString("account_number");
			int bal = rs.getInt("account_balance");
			int cId = rs.getInt("client_id");
			a = new Account(id, accNum, bal, cId);
			aList.add(a);
		}

		ctx.json(aList);
		rs.close();
		ptsmt.close();
	};

	//Adds an account.
	public static Handler addAccount = ctx -> {
		int cId = Integer.parseInt(ctx.pathParam("id"));
		Account a = ctx.bodyAsClass(Account.class);
		Connection conn = ConnUtil.createConnection();
		PreparedStatement ptsmt = conn.prepareStatement("INSERT into account values(?,?,?,?)");
		ptsmt.setInt(1, a.getId());
		ptsmt.setString(2, a.getAccNum());
		ptsmt.setInt(3, a.getBal());
		ptsmt.setInt(4, cId);
		ptsmt.execute();
		ctx.status(201);
		ptsmt.close();
	};
	 
}
