package com.vance.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.vance.domain.Company;
import com.vance.domain.DomainObject;

public class CompanyTask implements DBManager {
	
//	private Connection con=getConnection();
	private String insertStr="insert into Company(name,type,industry) values (?,?,?)";
	@Override
	public void insert(DomainObject domainObject) {
		insertCompany((Company) domainObject);
		
	}

	private void insertCompany(Company company) {
		if(con!=null){
			try {
				PreparedStatement ps=(PreparedStatement) con.prepareStatement(insertStr);
				ps.setString(1 , company.getName().toString());
				ps.setString(2, company.getType());
				ps.setString(3, company.getIndustry());
			} catch (SQLException e) {
				System.out.println("huh...errors occurs");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void query(DomainObject domainObject) {
		
	}

	
}
