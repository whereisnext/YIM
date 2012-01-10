package com.vance.view;
import java.util.List;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import com.vance.twitter.Status;
import com.vance.twitter.UserTweet;


public class TweetsDecks<T> extends ListView {

	private static final long serialVersionUID = 1L;
	
	private UserTweet userTweet;
	
	public TweetsDecks(String id,final List<? extends T> list) {
		super(id,list);
	}

	@Override
	protected void populateItem(ListItem item) {
			Status userStatus=(Status) item.getModelObject();
			item.add(new Label("listView_createDate",userStatus.getCreated_at()));
			item.add(new Label("listView_text",userStatus.getText()));
	}

	public UserTweet getUserTweet() {
		return userTweet;
	}

	public void setUserTweet(UserTweet userTweet) {
		this.userTweet = userTweet;
	}

	
	
	



	
}
