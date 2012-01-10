package com.vance.adapter;

import com.vance.domain.Company;
import com.vance.domain.DomainObject;

public class CompanyAdapter extends DomainAdapter {
//
public static Company adapter(com.vance.test.Company _company) {
		Company company=new Company();
		System.out.println("id is: "+_company.getId());
		company.setId(_company.getId());
		company.setIndustry(_company.getIndustry());
		company.setName(_company.getName());
		company.setType(_company.getType());
		return company;
//		
//		
	}
}
