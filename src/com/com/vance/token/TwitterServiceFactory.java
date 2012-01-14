package com.vance.token;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

public class TwitterServiceFactory implements ServiceFactory {
	private static OAuthService oauthService;
	
	private static  String consumerKeyValue="tCzt4iJfkNucRdO7IZYJQ";
	private static  String callBackUrl="http://176.34.61.2:8080/YIM";
	private static String consumerSecretValue="TyXBaAs26SlzUhDnES0p8C5EgOUAFqKsCZ8PXVx3Y5I";
	
	public TwitterServiceFactory(){
		initService();
	}
	
	@Override
	public void initService() {
		oauthService=new  ServiceBuilder().provider(TwitterApi.class).apiKey(consumerKeyValue).apiSecret(consumerSecretValue).
				callback(callBackUrl).build();
	}
	
	public static OAuthService getInstance(){
		if(TwitterServiceFactory.oauthService==null){
			new TwitterServiceFactory();
		}
		return oauthService;
	}

}
