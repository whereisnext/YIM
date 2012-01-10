package com.vance.yim;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.settings.ISecuritySettings;
import org.apache.wicket.util.crypt.ClassCryptFactory;
import org.apache.wicket.util.crypt.NoCrypt;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.xml.sax.InputSource;

//import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
//import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
//import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.vance.adapter.CompanyAdapter;
import com.vance.authenciate.CommonAuthencateToken;
import com.vance.test.Company;
import com.vance.test.Position;
import com.vance.test.Positions;
import com.vance.test.UserConnection;
import com.vance.test.UserProfile;
import com.vance.token.LinkedInPage;
import com.vance.token.LinkedInServiceFactory;
import com.vance.view.BasePage;

public class UserProfilePage extends WebPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ThreadLocal<HashMap<Token, String>> requestTokenTempPool = new ThreadLocal<HashMap<Token, String>>();
//	private static final String apiUrl = "http://api.linkedin.com/v1/people/~:(id,first-name,last-name,industry,headline,location:(name),distance,summary,specialties,proposal-comments,associations,positions,languages,skills,certifications,educations,public-profile-url)";
	protected static final String getPersonalUrl ="http://api.linkedin.com/v1/people/~:(first-name,last-name,headline,summary)";
	private static String getConnectionsUrl="http://api.linkedin.com/v1/people/~/connections:(headline,first-name,last-name)";
	// List<Position> positions = new ArrayList<Position>();
	private static OAuthService service = LinkedInServiceFactory.getOauthService();
	private UserProfile userProfile = null;

	public UserProfilePage() {
		super();

		
//		CssResourceReference rrefCSS = new CssResourceReference(BasePage.class,"/YIM/WebContent/WEB-INF/style.css");
//		ResourceLink<CssResourceReference> rlnkCSS = new ResourceLink<CssResourceReference>("stylesheet", rrefCSS);
//		add(rlnkCSS);
		if(getSession().getAttribute("userProfile")!=null){
//			UserProfile userProfile=(UserProfile) getSession().getAttribute("userProfile");
		}
		add(new Label("oauth_token",getSession().getAttribute("oauth_token").toString()));
		Token accessToken=(Token)getSession().getAttribute("accessToken");
		UserProfile userProfile=getPersonalProfileAPI(accessToken);
		System.out.println(userProfile.getFirstName());
		add(new Label("firstName",userProfile.getFirstName()));
		add(new Label("lastName",userProfile.getLastName()));
		add(new Label("summary",userProfile.getSummary().getValue()));
		
		UserConnection userConnection=getUserConnection(accessToken);
		
	}

	private UserConnection getUserConnection(Token accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, getConnectionsUrl);
		service.signRequest(accessToken, request);
		Response response=request.send();
		System.out.println(response.getBody());
		try {
			JAXBContext jaxbContext=JAXBContext.newInstance(UserConnection.class);
			Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
			byte[] bytes=response.getBody().getBytes();
			
			UserConnection userConnections=(UserConnection) unmarshaller.unmarshal(new BufferedInputStream(new ByteArrayInputStream(bytes)));
			return userConnections;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void makeup() {
		
	}

	// public class ValidateForm extends Form {
	// HttpServletResponse response;
	// public ValidateForm(String id) {
	// super(id);
	// add(new TextField("userEmailAddress"));
	// add(new SubmitLink("validateEmail"));
	// }
	//
	// public void onSubmit() {
	// PageParameters params = new PageParameters();
	// params.add("searchString", null);
	//
	// String url = "www.google.com";
	// // additional code here
	// //
	// getWebRequest().getHttpServletRequest().getRequestDispatcher(url).forward(request,
	// // response);
	// }
	// }
	private UserProfile getPersonalProfileAPI(Token accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, getPersonalUrl);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(UserProfile.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			byte[] bytes = response.getBody().toString().getBytes();
			UserProfile userProfile = (UserProfile) u
					.unmarshal(new BufferedInputStream(
							new ByteArrayInputStream(bytes)));
			return userProfile;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}