package com.vance.yim;

import org.apache.wicket.Session;
import org.apache.wicket.request.ClientInfo;
import org.apache.wicket.request.Request;

public class LinkedInTokenSession extends Session {
	
	private String token=null;
	
	public LinkedInTokenSession(Request request) {
		super(request);	
	}
	@Override
	public void cleanupFeedbackMessages() {

	}

	@Override
	public ClientInfo getClientInfo() {
		return null;
	}

}
