package auction.utils;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import auction.entity.Item;


public class DateTimeUtils {

	static final Logger logger = Logger.getLogger(DateTimeUtils.class);

	

	static public String getDateAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-YYYY");

		return dtf.print(dateTime);
	}

	
	
	static public String getTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm:ss");

		return dtf.print(dateTime);
	}

	
	
	static public String getDateTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-YYYY HH:mm");

		return dtf.print(dateTime);
	}

	
	
	static public String getPublishDateToLocaleString(Item item, Locale locale){
		
		DateTime publishDate = new DateTime(item.getPublishDate());
	//	DateTime startDate = new DateTime(item.getStartDate());
	//	DateTime finishDate = new DateTime(item.getFinishDate());
		DateTime now = new DateTime();
		
		if( item.getPublishDate() == null )
			return now.toString("d MMM yyyy HH:mm", locale);
		
		//if(publishDate.isAfter( now.plusMonths(2) ))
		//	return now.toString("d MMM yyyy HH:mm", locale);
		
		return publishDate.toString("d MMM yyyy HH:mm", locale);
			
	}
	

	
	static public String getStartDateToLocaleString(Item item, Locale locale){
		
	//	DateTime publishDate = new DateTime(item.getPublishDate());
		DateTime startDate = new DateTime(item.getStartDate());
	//	DateTime finishDate = new DateTime(item.getFinishDate());
		DateTime now = new DateTime();
		
		if( item.getStartDate() == null )
			return now.plusDays(1).toString("d MMM yyyy HH:mm", locale);
		
	//	if(startDate.isAfter( publishDate.plusWeeks(2) ))
	//		return now.toString("d MMM yyyy HH:mm", locale);
		
		return startDate.toString("d MMM yyyy HH:mm", locale);
			
	}



	static public String getFinishDateToLocaleString(Item item, Locale locale){
		
		//	DateTime publishDate = new DateTime(item.getPublishDate());
		//	DateTime startDate = new DateTime(item.getStartDate());
			DateTime finishDate = new DateTime(item.getFinishDate());
			DateTime now = new DateTime();
			
			if( item.getFinishDate() == null )
				return now.plusWeeks(1).toString("d MMM yyyy HH:mm", locale);
			
		//	if(startDate.isAfter( publishDate.plusWeeks(2) ))
		//		return now.toString("d MMM yyyy HH:mm", locale);
			
			return finishDate.toString("d MMM yyyy HH:mm", locale);
				
	}

	
	
	static public String getCountDownString(Item item, String day, String days){

		StringBuilder str = new StringBuilder();
		
		DateTime dateCurrent    = new DateTime();
		DateTime dateStart  	= new DateTime(item.getStartDate());
//		DateTime dateFinish 	= new DateTime(item.getFinishDate());
		DateTime datePublish 	= new DateTime(item.getPublishDate());
		
		logger.info(item.isPreTrading());
		logger.info(item.isTrading());
		logger.info(dateCurrent.toString("MM dd, YYYY HH:mm:ss"));
		logger.info(dateStart.toString("MM dd, YYYY HH:mm:ss"));
		logger.info(datePublish.toString("MM dd, YYYY HH:mm:ss"));
		
		
		if( item.isPreTrading() == true ){
			
			Duration duration = new Duration(dateCurrent, dateStart);
		
			int i=1;i=i/(i-1);
			
			if( duration.getStandardDays() < 1 ){

				//int i=1;i=i/(i-1);
				
				str
					//.append(dtf.print(dateStart));
					//  .append(DATE_FORMAT.format(item.getStartDate()));
					.append(dateStart.toString("MM dd, YYYY HH:mm:ss"));
				
				
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
			
		}
	
		return str.toString();
		//return str1;
	}
	
}
