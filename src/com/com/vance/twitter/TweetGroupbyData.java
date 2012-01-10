package com.vance.twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import net.sf.cglib.core.Local;

public class TweetGroupbyData implements DateGroupBy{
	private UserTweet userTweet;
	
	public TweetGroupbyData(UserTweet userTweet){
		this.userTweet=userTweet;
	}

	@Override
	public HashMap<Date, Integer> groupby() {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		HashMap<Date, Integer> groupbyResult=new HashMap<Date, Integer>();
//		Date groupbyDate=
		int totalCount=0;
		for(Status status:userTweet.getStatus()){
			try {
				
				int myMonth=simpleDateFormat.parse(status.getCreated_at()).getMonth()+1;
				int myYear=simpleDateFormat.parse(status.getCreated_at()).getYear();
				Date myDate=new Date();
				myDate.setMonth(myMonth);
				myDate.setYear(myYear);
				if(groupbyResult.containsKey(myDate)){
					totalCount=groupbyResult.get(myDate)+1;
					groupbyResult.put(myDate,totalCount);
				}else{
					groupbyResult.put(myDate,1);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return groupbyResult;
	}

	
	private UserTweet getDate(UserTweet userTweet) {
		
		return null;
	}
	
	public static void main(String args[]){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		Date myDate;
		try {
			myDate = simpleDateFormat.parse("Wed Dec 26 15:57:40 +0000 2011");
			System.out.println(myDate.getMonth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
