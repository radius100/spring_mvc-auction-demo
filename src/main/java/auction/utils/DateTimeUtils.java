package auction.utils;

import org.joda.time.DateTime;

import auction.entity.TradePool;

public class DateTimeUtils {

	static public String getStringDate(TradePool tradePool){
		
		DateTime dateTime = new DateTime(tradePool.getDate());
		
		return Integer.toString(dateTime.getYear()) + "-" + 
				Integer.toString(dateTime.getMonthOfYear()) + "-" +
				Integer.toString(dateTime.getDayOfMonth());
		
	}
	
	static public String getStringTime(TradePool tradePool){
		
		DateTime dateTime = new DateTime(tradePool.getDate());
		
		return Integer.toString(dateTime.getHourOfDay()) + ":" + 
				Integer.toString(dateTime.getMinuteOfHour());
		
	}

}
