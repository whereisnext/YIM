package com.vance.token;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.oauth.OAuthService;

public class LinkedInServiceFactory implements ServiceFactory{
	private static String consumerKeyValue = "911q5q45g3cv";
	private static String consumerSecretValue = "wM62p1IHlaCzljad";
	private static String callBackUrl="http://localhost:8080/YIM";
	private static OAuthService oauthService;
	private final static void initLinkedInOAuthServiceInstance(){
		setOauthService(new ServiceBuilder().provider(LinkedInApi.class).apiKey(consumerKeyValue).apiSecret(consumerSecretValue).
				callback(callBackUrl).build());
//		return oauthService;
	}
	@Override
	public void initService() {		
		initLinkedInOAuthServiceInstance();
	}
	public static OAuthService getOauthService() {
		return oauthService;
	}
	public static void setOauthService(OAuthService oauthService) {
		LinkedInServiceFactory.oauthService = oauthService;
	}
	
	

}
