package auction.utils;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import auction.constant.Constants;
import auction.entity.Item;


public class DateTimeUtils {

	static final Logger logger = Logger.getLogger(DateTimeUtils.class);

	

	static public String getDateAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATEFORMATTER_STRING);

		return dtf.print(dateTime);
	}

	
	
	static public String getTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.TIMEFORMATTER_STRING);

		return dtf.print(dateTime);
	}

	
	
	static public String getDateTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATETIMEFORMATTER_STRING);

		return dtf.print(dateTime);
	}

	
	static public String getCountDownString(Item item, String day, String days){

		StringBuilder str = new StringBuilder();
		
		DateTime dateCurrent    = new DateTime();
		DateTime dateStart  	= new DateTime(item.getStartDate());
		DateTime dateFinish 	= new DateTime(item.getFinishDate());
		
		Duration duration = new Duration(0);
		
		logger.info(item.isPreTrading());
		logger.info(item.isTrading());
		logger.info(dateCurrent.toString(Constants.DATETIMEFORMATTER_STRING));
		logger.info(dateStart.toString(Constants.DATETIMEFORMATTER_STRING));
		
		
		if( item.isPreTrading() )
			duration = new Duration(dateCurrent, dateStart);
		
		else if( item.isTrading() ) 
			duration = new Duration(dateCurrent, dateFinish);
		
	//	if( duration == null )
	//		return "";
		
		if( duration.getStandardDays() < 1 ){

			str
				.append(dateStart.toString(Constants.DATETIMECOUNTDOWNFORMATTER_STRING, Locale.ENGLISH));
				
		}
			
		else if( duration.getStandardDays() == 1 ){
		
			str
				.append("!")
				.append(duration.getStandardDays())
				.append(" ")
				.append(day);
			}
			
		else if( duration.getStandardDays() > 1 ){
			str
				.append("!")
				.append(duration.getStandardDays())
				.append(" ")
				.append(days);
		}
			
		
		logger.info("--->>");
		logger.info(str.toString());
		
		return str.toString();
	}
	
}
