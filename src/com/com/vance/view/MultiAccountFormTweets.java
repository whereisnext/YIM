package com.vance.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.vance.twitter.Status;
import com.vance.twitter.UserTweet;


public class MultiAccountFormTweets<T> extends Form {
	
	private ArrayList userStatuses;

	public MultiAccountFormTweets(String id,final List<? extends T> list) {
		super(id);
		userStatuses=(ArrayList) list;
		addComponent();
	}

	private void addComponent() {
		IModel<?> aaa=new Model<String>();
		IModel<?> bbb=new Model<String>();
//		 userStatuses=userTweet.getStatus();
		TweetsDecks tweetsDecks=new TweetsDecks("tweetsDeck",userStatuses){
			@Override
			protected void populateItem(ListItem item) {
					Status userStatus=(Status) item.getModelObject();
					item.add(new Label("listView_createDate",userStatus.getCreated_at()));
					item.add(new Label("listView_text",userStatus.getText()));
			}
		};
		add(tweetsDecks);
	}

}
