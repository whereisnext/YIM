package com.vance.node;

import java.util.Enumeration;

//import org.apache.jasper.tagplugins.jstl.core.Set;

public enum StartUP {
	summary,title;
	public String getDomainClassName(){
		return this.name();
	}
	
	public static void main(String args[]){
		for(StartUP s:StartUP.values()){
			System.out.println(s.getDomainClassName());
		}
	
	}
	
	
}
