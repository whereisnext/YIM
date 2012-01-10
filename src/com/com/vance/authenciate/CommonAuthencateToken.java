package com.vance.authenciate;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.vance.token.LinkedInServiceFactory;

public class CommonAuthencateToken {
	private static OAuthService service = LinkedInServiceFactory.getOauthService();
	public CommonAuthencateToken(){
		
	}
	public Token getAccessToken() {
		
		return null;
	}
	public Token getAccessToken(Token requestToken, Verifier oauthVerifier) {
		if(service!=null){
			return service.getAccessToken(requestToken, oauthVerifier);
		}
		return null;
	}
	
}
