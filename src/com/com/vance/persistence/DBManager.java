package com.vance.persistence;

import java.sql.Connection;
import java.util.HashMap;

import com.vance.domain.DomainObject;

public interface DBManager{
	
	
	public Connection con=DBFactory.getDBConnection();
	
	public void insert(DomainObject domainObject);
	
	public void query(DomainObject domainObject);
	
	
}