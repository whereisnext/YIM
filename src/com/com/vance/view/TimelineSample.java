package com.vance.view;

import org.apache.wicket.markup.html.WebPage;

import com.vance.view.timelineComponent.TimeLineComponent;

public class TimelineSample extends WebPage {
	public TimelineSample(){
		TimeLineComponent timelineComponent=new TimeLineComponent("timeLine");
		add(timelineComponent);
	}
}
