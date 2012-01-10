package com.vance.view.timelineComponent;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.vance.yim.RootPathUtil;

public class TimeLineComponent extends Panel implements IHeaderContributor{
	private Component gridComponent;
	
	public TimeLineComponent(String id) {
		super(id);
		gridComponent = new WebMarkupContainer("grid").setOutputMarkupId(true);
	    add(gridComponent);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void onRender() {
		
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		final String contextPath = RootPathUtil.getRootPath()+""; // dito with servlet api
		final String localPath="http://localhost:9999/timeline/";
		response.renderJavaScriptReference(localPath+ "api/timeline-api.js?bundle=true");
		response.renderJavaScriptReference(localPath+ "api/timeline-api-vance.js");
		
	}
}
