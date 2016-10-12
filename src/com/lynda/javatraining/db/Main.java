package com.lynda.javatraining.db;

import java.sql.Connection;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class Main {		
	public static void main(String args[]) throws Exception {
		System.out.println("Starting application");
		ConnectionManager.getInstance().setDBType(DBType.MYSQL);
		AdminManager.displayAllRows();
		int adminId = InputHelper.getIntegerInput("Select a row to update: ");
		Admin bean = AdminManager.getRow(adminId);
		if(bean==null){
			System.err.println("Row not found.");
			return;
		}
		bean.setPassword(InputHelper.getInput("Enter new password: "));
		
		Connection conn = ConnectionManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		if(AdminManager.update(bean))
			System.out.println("Success!");
		else
			System.out.println("sorry error on updates");
		
//		conn.commit();
//		System.out.println("Transaction committed.");
		
		conn.rollback();
		System.out.println("Transaction rolled back.");
		
		ConnectionManager.getInstance().close();
	}
}
