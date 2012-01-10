package com.vance.view;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

//import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;

import org.apache.wicket.markup.MarkupType;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.oauth.OAuthService;

import com.vance.authenciate.CommonAuthencateToken;
import com.vance.domain.Person;
import com.vance.test.UserProfile;
import com.vance.token.LinkedInPage;
import com.vance.token.LinkedInServiceFactory;
import com.vance.token.TwitterOuathPage;
import com.vance.token.TwitterServiceFactory;
import com.vance.twitter.MyTwitterSession;
import com.vance.view.timelineComponent.TimeLineComponent;
import com.vance.yim.UserProfilePage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = -9008871201606758899L;
	protected static final String apiUrl = "http://api.linkedin.com/v1/people/~:(first-name,last-name,headline)";
	protected Token requestToken = null;
	private static OAuthService service = LinkedInServiceFactory.getOauthService();
	private UserProfile userProfile = null;
	private Token accessToken = null;
	private Verifier oauthVerifier=null;

	public BasePage() {

		try {
			validateSessionToken();
			if (getSession().getAttribute("requestToken") != null) {
				Token requestToken = (Token) getSession().getAttribute("requestToken");
				if (getSession().getAttribute("oauth_verifier") != null) {
					oauthVerifier= new Verifier(getSession().getAttribute("oauth_verifier").toString().split("=")[1]);
					
					if (getSession().getAttribute("ouahTokenFrom").toString().contains("fromLinkedIn")) {
						accessToken = service.getAccessToken(requestToken,
								oauthVerifier);
						userProfile = getPersonalProfileAPI(accessToken);
						if (userProfile != null) {
							getSession().setAttribute("accessToken",accessToken);
							setResponsePage(UserProfilePage.class);
						}
					}
				   if(getSession().getAttribute("ouahTokenFrom").toString().contains("fromTwitter")){
					   OAuthService twitterService=TwitterServiceFactory.getInstance();
					   Token accessToken=twitterService.getAccessToken(requestToken, oauthVerifier);
					   System.out.println("Twitter token is: "+accessToken);
					   MyTwitterSession myTwitter=new MyTwitterSession();
					   myTwitter.setAccessToken(accessToken);
					   getSession().setAttribute("myTwitterSession", myTwitter.getAccessToken());
					   PageParameters params=new PageParameters();
					   params.add("","");
					   setResponsePage(UserTweetsPage.class);
				   }
				}
			}
			Form<Void> form = new Form<Void>("ajaxTest");
			add(form);
			form.add(new AjaxButton("ajaxTest_link") {
				@Override
				protected void onError(AjaxRequestTarget arg0, Form<?> arg1) {
				}

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					if (userProfile != null && accessToken != null) {
						System.out.println("accessToken is: " + accessToken);
						getSession().setAttribute("accessToken", accessToken);
						setResponsePage(UserProfilePage.class);
					} else {
						setResponsePage(LinkedInPage.class);
					}
					getSession().setAttribute("ouahTokenFrom", "fromLinkedIn");
				}
			});
			Form<Void> twitterForm = new Form<Void>("oauthTwitter");
			add(twitterForm);
			twitterForm.add(new AjaxButton("ajaxCall_oauthTwitter") {
				@Override
				protected void onSubmit(AjaxRequestTarget arg0, Form<?> arg1) {
					getSession().setAttribute("ouahTokenFrom", "fromTwitter");
					setResponsePage(TwitterOuathPage.class);
				}
				@Override
				protected void onError(AjaxRequestTarget arg0, Form<?> arg1) {

				}
			});
			
			add(new Label("footer_text", "This is By Vance Inc"));
		} catch (Exception e) {
			System.out.println(getSession().getAttribute("oauth_verifier"));
		}
	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavaScriptReference(new PackageResourceReference(
				BasePage.class,
				"http://yui.yahooapis.com/3.4.1/build/yui/yui-min.js"));
		// response.renderCSSReference(new
		// PackageResourceReference(AbstractCalendar.class,
		// "assets/skins/sam/calendar.css"));
	}

	public MarkupType getMarkupType() {
		return MarkupType.HTML_MARKUP_TYPE;
	}

	protected void setHeaders(WebResponse response) {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	}

	protected void validateSessionToken() {
		Session session = getSession();
		if (getRequest().getUrl().getQueryParameter("oauth_token") != null) {
			System.out.println("getSession().getAttribute('oauthTokenFrom'): "+getSession().getAttribute("ouahTokenFrom").toString());
			String oauth_token = getRequest().getUrl()
					.getQueryParameter("oauth_token").toString();
			String oauth_verifier = getRequest().getUrl()
					.getQueryParameter("oauth_verifier").toString();
			session.setAttribute("oauth_token", oauth_token);
			session.setAttribute("oauth_verifier", oauth_verifier);
		}
	}
	
	private UserProfile getPersonalProfileAPI(Token accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, apiUrl);
		service.signRequest(accessToken, request);
		Response response = request.send();
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
	
	private void getTweets(Token accessToken,OAuthService twitterService){
		String tweetsUrl="https://api.twitter.com/1/statuses/friends_timeline.json?include_entities=true";
		OAuthRequest request = new OAuthRequest(Verb.GET, tweetsUrl);
		twitterService.signRequest(accessToken, request);
		Response response=request.send();
		System.out.println(response.getBody());
	}
	
	
}
