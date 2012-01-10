package com.vance.token;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
public class LinkedInPage extends WebPage {
	public LinkedInPage(){
	}
	@Override
	public void onBeforeRender(){
		  	super.onBeforeRender();	  	
			OAuthService oauthService= LinkedInServiceFactory.getOauthService();
			Token requestToken=oauthService.getRequestToken();
			getSession().setAttribute("requestToken", requestToken);
			final String authUrl = oauthService.getAuthorizationUrl(requestToken);
			
			RedirectRequestHandler target = new RedirectRequestHandler(authUrl) {
	             @Override
	             public void respond(IRequestCycle requestCycle) {
	             WebResponse response = (WebResponse)requestCycle.getResponse();
	             response.reset();
	             ((HttpServletResponse)response.getContainerResponse()).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	             response.sendRedirect(authUrl);
	           }
	         }; 
	         getRequestCycle().scheduleRequestHandlerAfterCurrent(target);
	} 
	@Override
	public void onAfterRender(){
		super.onAfterRender();
	}	
}
