package com.vance.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBFactory {
	private static Connection con;
	public static Connection getDBConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/YIM";
			String root="root";
			String pw="jsvskk";
			con=DriverManager.getConnection(url,root,pw);
			
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	
	
	
	
}
