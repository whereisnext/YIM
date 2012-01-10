package com.vance.token;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import org.scribe.builder.ServiceBuilder;
//import org.scribe.builder.api.LinkedInApi;
//import org.scribe.model.OAuthRequest;
//import org.scribe.model.Response;
//import org.scribe.model.Token;
//import org.scribe.model.Verb;
//import org.scribe.model.Verifier;
//import org.scribe.oauth.OAuthService;


//
//public class TokenManager {
//	private static final String PROTECTED_RESOURCE_URL = "";
//	private String consumerKeyValue = "911q5q45g3cv";
//	private String consumerSecretValue = "wM62p1IHlaCzljad";
//	private LinkedInAccessToken accessToken;
//	final LinkedInApiClientFactory factory = LinkedInApiClientFactory
//			.newInstance(consumerKeyValue, consumerSecretValue);
//	final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory
//			.getInstance().createLinkedInOAuthService(consumerKeyValue,
//					consumerSecretValue);
//
//	private ThreadLocal<LinkedInRequestToken> tokenList = new ThreadLocal();
//	
//	/*
//	 * initialize token at first
//	 */
//	
//	public TokenManager(){
//		requestToken();
//	}
//
//	private LinkedInRequestToken requestToken() {
//		LinkedInRequestToken token;
//		if (tokenList != null && tokenList.get()==null) {
//			token = oauthService.getOAuthRequestToken();
//			tokenList.set(token);
//			return token;
//		}
//		return null;
//	}
//
//	public void setAccessToken() {
//		LinkedInRequestToken token = requestToken();
//		if (token != null) {
//			tokenList.set(token);
//		}
//	}
//
//	public LinkedInRequestToken getAccessToken(Date date) {
//		if (date.toString().compareToIgnoreCase(
//				tokenList.get().getExpirationTime().toString()) > 0) {
//			System.out.println("Token expired!");
//			tokenList.set(null);
//			return requestToken();
//		} else {
//			System.out.println("Current Token is vaild");
//			System.out.println("Current time is: "+tokenList.get().getToken());
//			return tokenList.get();
//		}
//		
//	}
//	
//	

//	public static void main(String args[]) throws ParseException {
//		 OAuthService service = new ServiceBuilder()
//         .provider(LinkedInApi.class)
//         .apiKey("911q5q45g3cv")
//         .apiSecret("wM62p1IHlaCzljad")
//         .build();
//		
//		TokenManager tf = new TokenManager();
//		tf.setAccessToken();
//		// System.out.println("AccessToken is: "+tf.getToken().getToken());
//		// System.out.println("Token Expire Time is: "+tf.getToken().getExpirationTime());
//		//
//		// Date date=new Date();
//		// System.out.println(date.getTime());
////		SimpleDateFormat df = new SimpleDateFormat("yyyy");
////		for(int j=0;j<10;j++){
////			tf.getAccessToken(df.parse("2010"));
////		}
//		
////		for (int i = 0; i < 10; i++) {
//////			SimpleDateFormat df = new SimpleDateFormat("yyyy");
////			System.out.println("New Token is: "+tf.getAccessToken(df.parse("2012")).getToken());
////		}
//		
//		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
//		Token requestToken=service.getRequestToken();
//		Verifier verifier=new Verifier("65001");
//		
//		Token accessToken = service.getAccessToken(requestToken, verifier);
//		service.signRequest(accessToken, request);
//		 Response response = request.send();
//		    System.out.println("Got it! Lets see what we found...");
//		    System.out.println();
//		    System.out.println(response.getBody());
//		
//		
////		
////		 String consumerKeyValue = "911q5q45g3cv";
////		 String consumerSecretValue = "wM62p1IHlaCzljad";
////		
////		LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
////		LinkedInRequestToken token=tf.getAccessToken(df.parse("2010"));
////		LinkedInApiClient client = factory.createLinkedInApiClient(token.getToken(), token.getTokenSecret());
////		System.out.println(client.getApiConsumer().getToken());
//	}

//}
