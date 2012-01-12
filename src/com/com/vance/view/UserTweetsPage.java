package com.vance.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.vance.domain.Event;
import com.vance.domain.TimeLine;
import com.vance.token.TwitterServiceFactory;
import com.vance.twitter.DateGroupBy;
import com.vance.twitter.Status;
import com.vance.twitter.TweetGroupbyData;
import com.vance.twitter.UserTweet;
import com.vance.view.timelineComponent.TimeLineComponent;
import com.vance.yim.RootPathUtil;

public class UserTweetsPage extends BasePage {

	private static OAuthService twitterService = TwitterServiceFactory
			.getInstance();

	public UserTweetsPage() {
		super();
		Token accessToken = (Token) getSession().getAttribute("myTwitterSession");


		String mytweets = getTweets(accessToken, twitterService);
		
//		System.out.println("mytweets is: "+mytweets);
		
		byte[] bytes = mytweets.getBytes();
		InputStream myTweets = new ByteArrayInputStream(bytes);

		try {
			JAXBContext jaxb = JAXBContext.newInstance(UserTweet.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			
			File file = new File("/home/vance/twitter sample.xml");
			UserTweet userTweet = (UserTweet) unmarshaller.unmarshal(myTweets);
			String rootPath=RootPathUtil.getRootPath();
			System.out.println(RootPathUtil.getRootPath());
			
			
			JAXBContext jaxb2 = JAXBContext.newInstance(TimeLine.class);
			Marshaller marShaller=jaxb2.createMarshaller();
			TimeLine timeLine=new TimeLine();
			ArrayList<Event> events=new ArrayList<Event>();
			for(Status status:userTweet.getStatus()){
				Event event=new Event();
				event.setStart(status.getCreated_at());
//				event.setEnd(status.getCreated_at());
//				event.setText(status.getText());
				event.setIsDuration(false);
				event.setTitle(status.getText());
				events.add(event);
			}
			timeLine.setEvents(events);
			File outputFile=new File(rootPath+"vancezhao.xml");
			if(outputFile.exists()){
				outputFile.delete();
			}
			System.out.println(outputFile.getAbsolutePath());
			OutputStream outPutStream=new FileOutputStream(outputFile);
			marShaller.marshal(timeLine, outPutStream);
			DateGroupBy myDateGroupBy=new TweetGroupbyData(userTweet);
			String mydataValues=appendingDataValues(myDateGroupBy.groupby());
			renderingCharts(mydataValues);
			renderingDatas(userTweet.getStatus());
			TimeLineComponent timelineComponent=new TimeLineComponent("timeLine");
			
			add(timelineComponent);
		} catch (JAXBException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	private void renderingDatas(ArrayList mydataValues) {
		
//		add(new MultiAccountFormTweets("test1",mydataValues));
	}

	private String appendingDataValues(HashMap<Date,Integer> userTweet) {
		String flag=",";
		int i=0;
		StringBuilder myDataValues=new StringBuilder("var myDataValues=[");
		int totalSize=2;
		Set keySet=userTweet.keySet();
		Iterator it=keySet.iterator();
		Date myDate=(Date) it.next();
		int limit=10;
		while(it.hasNext()){
			if(limit>0){
			myDataValues.append("{category:'" +myDate+
					"',text:'" +userTweet.get(myDate).toString().replace("'","")+
					"'}"+flag);
			}
			myDate=(Date)it.next();
			limit--;
		}
		myDataValues.append("{category:'" +""+
				"',text:'" +""+
				"'}");
		return myDataValues.append("];").
				append("var mychart = new Y.Chart({dataProvider:myDataValues,render:'#userTweets',type:'column', styles:'styleDef',stacked:true})").
				toString();
	}

	private void renderingCharts(final String mydataValues) {
		
		final AbstractDefaultAjaxBehavior behave = new AbstractDefaultAjaxBehavior() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1088173170076107816L;
			protected void respond(final AjaxRequestTarget target) {
		        target.add(new Label("foo", "Yeah I was just called from Javascript!"));
		        
		    }
		    @Override
		    public void  renderHead(Component component, IHeaderResponse response){
		    	response.renderString("<script type=\"text/javascript\">(function() {YUI().use('charts', function (Y) { " +
		    			"var styleDef={axes:{values:{label:{rotation:-45,color:\"#RED\"}},date:{label:{rotation:-45,color:\"#RED\"}}}};" +
		    			""+mydataValues+" ;})" +
		    			";})();" +"\n"+
		    			"</script>");
		    }
		};
		add(behave);
	}

	private String getTweets(Token accessToken, OAuthService twitterService) {
		String tweetsUrl = "https://api.twitter.com/1/statuses/friends_timeline.json?include_entities=true";
		String retweetedByMe = "http://api.twitter.com/1/statuses/retweeted_by_me.xml?include_entities=true&count=3";
		String publicStream = "http://api.twitter.com/1/statuses/user_timeline.xml?count=100&include_entities=false&trim_user=true&contributor_details=false";
		OAuthRequest request = new OAuthRequest(Verb.GET, publicStream);
		twitterService.signRequest(accessToken, request);
		Response response = request.send();
		return response.getBody().toString();
	}
	
	public void renderHead(IHeaderResponse response) {
		response.renderJavaScriptReference(new PackageResourceReference(
				UserTweetsPage.class,
				"yui-min.js"));
		// response.renderCSSReference(new
		// PackageResourceReference(AbstractCalendar.class,
		// "assets/skins/sam/calendar.css"));
	}
}
