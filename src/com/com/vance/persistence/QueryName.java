package com.vance.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.vance.domain.DomainObject;

public abstract class QueryName implements DBManager {

//	private static Connection con=DBManager.con;
	
	@Override
	public void query(DomainObject db) {
		if(con!=null){
			System.out.println("This is from MYSQL");
			Statement stat;
				try {
					stat = con.createStatement();
					ResultSet rs=stat.executeQuery("Select * from UserProfile");
					System.out.println();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}

		}
	}
	
	
	
}
