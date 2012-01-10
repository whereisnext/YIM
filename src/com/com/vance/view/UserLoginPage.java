package com.vance.view;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.vance.test.UserProfile;
import com.vance.token.LinkedInServiceFactory;
import com.vance.token.TwitterServiceFactory;
import com.vance.twitter.MyTwitterSession;
import com.vance.yim.UserProfilePage;



public class UserLoginPage extends BasePage {
	

	
	public UserLoginPage(){
		
	}
	
	
	
	
}
