package com.vance.yim;

//import org.apache.wicket.Page;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.builder.api.TwitterApi;

import com.vance.token.LinkedInServiceFactory;
import com.vance.token.ServiceFactory;
import com.vance.view.BasePage;
import com.vance.view.UserLoginPage;

public class YIMApplication extends WebApplication {
	private static Object[] serviceNames = { LinkedInApi.class};
	
	@Override
	public void init(){
		 URL webroot;
		try {
			webroot = WebApplication.get().getServletContext().getResource("/");
			System.out.println("rootPath is: "+webroot.getPath());
			RootPathUtil.setRootPath(webroot.getPath());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Class getHomePage() {
		initServiceInstance();
		return UserLoginPage.class;
	}

	private void initServiceInstance() {
		ServiceFactory sf=null;
		for(Object serviceName:serviceNames){
			if(serviceName.getClass().isInstance(Api.class)){
					new LinkedInServiceFactory().initService();
				}
			}
		
	}



	@Override
	public Session newSession(Request request, Response response) {
		return new LinkedInTokenSession(request);
	}

}
