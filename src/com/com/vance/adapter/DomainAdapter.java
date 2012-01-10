package com.vance.adapter;

import com.vance.domain.DomainObject;

public abstract class DomainAdapter<T extends DomainObject> {
	private T domainObject;
	public static DomainObject adapter(Object obj) {
		
		
		return (DomainObject)obj;
		
		
	}
}
