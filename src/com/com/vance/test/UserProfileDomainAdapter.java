package com.vance.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.vance.adapter.CompanyAdapter;
import com.vance.adapter.DomainAdapter;
import com.vance.domain.DomainObject;
import com.vance.test.Position;

public class UserProfileDomainAdapter extends DomainAdapter{
	

    public static void main(String args[]) throws Exception{
    
        JAXBContext context = JAXBContext.newInstance(
            "com.vance.test");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UserProfile items = (UserProfile)unmarshaller.unmarshal(
            new FileReader("/home/vance/Person.xml"));
//        List<Item> listOfItems = items.getItem();
//        for(Item item : listOfItems){
//            System.out.println("Name = " + item.getFirstName()+ 
//                ",  Id = " + item.getId());
//       }
        
        Positions aaa=items.getPositions();
        
        
        for(Position po:aaa.getPosition()){
        	System.out.println("id is: "+po.getId());
        	System.out.println("summary is: "+po.getSummary());
        	System.out.println("title is: "+po.getTitle());
        	Company company=po.getCompany();
        	
        	System.out.println("my company id is: "+po.getCompany().getType());
        	com.vance.domain.Company _company=CompanyAdapter.adapter(po.getCompany());
        }

    }

	  
}
