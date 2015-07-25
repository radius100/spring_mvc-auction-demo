package auction.utils;

import java.util.Date;

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
	
	
	
	static public Item createItemDateMessage4IndexJsp(Item item) {

		DateTime dateStart = new DateTime(item.getStartDate());
		DateTime dateFinish = new DateTime(item.getFinishDate());
		DateTime dateCurrent = new DateTime();

		dateCurrent = dateCurrent.minusMinutes(25);
		// dateStart=dateStart.minusMinutes(35);
		dateFinish = dateFinish.plusDays(2);

		if (dateCurrent.isBefore(dateStart)) {

			Duration duration1 = new Duration(dateCurrent, dateStart);
			long d1 = duration1.getStandardDays();
			long h1 = duration1.getStandardHours();

			if (d1 >= 1) {
				item.setDateMessage(1);
				item.setDateValue(d1);
			} else if (h1 >= 1 && h1 <= 24) {
				item.setDateMessage(2);
				item.setDateValue(h1);
			}

			else if (h1 < 1)
				item.setDateMessage(3);

		} else if (dateCurrent.isBefore(dateFinish) && dateCurrent.isAfter(dateStart)) {

			Duration duration2 = new Duration(dateCurrent, dateFinish);
			long d2 = duration2.getStandardDays();
			long h2 = duration2.getStandardHours();

			if (d2 >= 1) {
				item.setDateMessage(4);
				item.setDateValue(d2);
			}

			else if (h2 >= 1 && h2 <= 24) {
				item.setDateMessage(5);
				item.setDateValue(h2);
			}

			else if (h2 < 1)
				item.setDateMessage(6);
		}

		return item;
	}
}
