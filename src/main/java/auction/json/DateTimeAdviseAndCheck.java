package auction.json;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import auction.entity.Item;

public class DateTimeAdviseAndCheck {

	@Expose private String publishDateMin;
	@Expose private String publishDateMax;
	@Expose private String publishDate;
	
	@Expose private String startDateMin;
	@Expose private String startDateMax;
	@Expose private String startDate;
	
	@Expose private String finishDateMin;
	@Expose private String finishDateMax;
	@Expose private String finishDate;
	
	@Expose private boolean editExpired;
	
	DateTime now = new DateTime();
	DateTime publish;
	DateTime start;
	DateTime finish;
	
	private Locale locale;
	private Item item;
	private String parseString = "d MMM yyyy HH:mm";
	
	String publishDateInputBox; 
	String startDateInputBox; 
	String finishDateInputBox;
	
	static final Logger logger = Logger.getLogger(DateTimeAdviseAndCheck.class);
		
	public DateTimeAdviseAndCheck setPublishDateInputBox(String publishDateInputBox){
		
		this.publishDateInputBox=publishDateInputBox;
		return this;
	}
	
	
	public DateTimeAdviseAndCheck setStartDateInputBox(String startDateInputBox){
		
		this.startDateInputBox=startDateInputBox;
		return this;
	}
	
	
	public DateTimeAdviseAndCheck setFinishDateInputBox(String finishDateInputBox){
		
		this.finishDateInputBox=finishDateInputBox;
		return this;
	}
	

	public DateTimeAdviseAndCheck setItem(Item item){
		
		this.item = item;
		return this;
	}

	
	public DateTimeAdviseAndCheck setLocale(Locale locale){
		
		this.locale = locale;
		return this;
	}

	
	public void fillPublishDateMinMaxDate(DateTime publish){
		
		publishDate = publish.toString(parseString, locale);
		publishDateMin = now.toString(parseString, locale);
		publishDateMax = now.plusWeeks(1).toString(parseString, locale);
		
	}


	public void fillStartDateMinMaxDate(DateTime start){
		
		startDate = start.toString(parseString, locale);
		startDateMin = publish.toString(parseString, locale);
		startDateMax = publish.plusWeeks(1).toString(parseString, locale);
		
	}
	
	
	public void fillFinishDateMinMaxDate(DateTime finish){
		
		finishDate = finish.toString(parseString, locale);
		finishDateMin = start.plusDays(1).toString(parseString, locale);
		finishDateMax = start.plusMonths(1).toString(parseString, locale);
				
	}

	
	public void checkAndBuildVer2(){
		
		//item
	
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-YYYY HH:mm");
		formatter.withLocale(locale);
		
		publishDateInputBox = "30-07-2015 01:00";
    	startDateInputBox = "01-08-2015 01:00";
    	finishDateInputBox = "02-08-2015 01:00";
		
		DateTime publishInput;
		DateTime startInput;
		DateTime finishInput;
		
		//item.setFinishDate(new DateTime().plusMonths(1).toDate());
		
		try {
			
			publishInput = formatter.parseDateTime(publishDateInputBox);
	
		} catch(Exception e) {
			
			logger.info("catch publishDate");
        	if(item.getPublishDate() != null)
        		publishInput = new DateTime(item.getPublishDate());
        	else
        		publishInput = new DateTime(now);
        } 
		
		
		try {
		
			startInput = formatter.parseDateTime(startDateInputBox);
		
		} catch(Exception e) {
		
			logger.info("catch startDate");
			if(item.getStartDate() != null)
        		startInput = new DateTime(item.getStartDate());
        	else
        		startInput = new DateTime(publishInput);
        }
		
		
		try {
			
			finishInput = formatter.parseDateTime(finishDateInputBox);
		
		} catch(Exception e) {
		
			logger.info("catch finishDate");
			if(item.getFinishDate() != null)
        		finishInput = new DateTime(item.getFinishDate());
        	else
        		finishInput = new DateTime(startInput.plusWeeks(1));
        } 
		
		//logger.info(publishInput.toString());
		//logger.info(startInput.plusDays(1).toString());
		//logger.info(finishInput.plusDays(2).toString());
		
		
		if( publishInput.isBeforeNow() )
			editExpired=true;
		
		else{
			
			Duration nowPublish = new Duration(publishInput,now);
			Duration publishStart = new Duration(publishInput,startInput);
			Duration publishFinish = new Duration(publishInput,finishInput);
			Duration startFinish = new Duration(startInput,finishInput);
			
			
			//if( publishStart.getStandardDays() > 14 )
				
			
			/*	
			if( startInput.isBefore(publishInput) )
				publishInput=startInput;
			
			if( publishInput.plusWeeks(2).isBefore(startInput) )
				startInput=publishInput.plusWeeks(2);

			if( publishInput.plusMonths(1).isBefore(finishInput) )
				finishInput=publishInput.plusMonths(1);
			*/
			//if( publishInput.plusDays(1).isAfter(startInput) )
			
		}
		
	}
	
	public void checkAndBuild() {
		
		if( item.getPublishDate() == null ){
			
			publish = new DateTime(now);
			start = new DateTime(now);
			
			DateTime finishTemp = new DateTime(start.plusDays(3));
			finish = new DateTime(finishTemp);
			
			fillPublishDateMinMaxDate(publish);
			
			fillStartDateMinMaxDate(start);
			
			fillFinishDateMinMaxDate(finish);
			
			//DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-YYYY HH:mm");
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-YYYY HH:mm");
			formatter.withLocale(locale);
			DateTime dt = formatter.parseDateTime("03-08-2015 12:17");
			
			logger.info(dt.toString());
			item.setStartDate(dt.toDate());
			
			//DateTime publishDateInput new DateTime();// publishDateInputBox; 
			//startDateInputBox; 
			//finishDateInputBox;
			
			
		}
		
		else if( item.getPublishDate().after(new Date()) ) {
				
		
			if( publishDateInputBox == null )
				publish = new DateTime( item.getPublishDate() );
			else 
				publish = new DateTime(publishDateInputBox);
			
			if( publish.isBefore(now) )
				publish = now;	
				
				
			if( startDateInputBox == null )
				start = new DateTime( item.getStartDate() );
			else 
				start = new DateTime(startDateInputBox);
		
			if( start.isBefore(publish) )
				start = publish;
				
			if( publish.plusWeeks(1).isBefore(start) )
				start = publish.plusWeeks(1);
				
		
			if( finishDateInputBox == null )
				finish = new DateTime( item.getFinishDate() );
			else 
				finish = new DateTime(finishDateInputBox);
					
			if( finish.isBefore(start) )
				finish = start.plusDays(1);	
	
			if( publish.plusMonths(1).isBefore(finish) )
				finish = publish.plusMonths(1);	
			
		}
		else { 
			
			//fillPublishDateMinMaxDate( new DateTime(item.getPublishDate()) );
			
			//fillStartDateMinMaxDate( new DateTime(item.getStartDate()) );
			
			//fillFinishDateMinMaxDate( new DateTime(item.getFinishDate()) );
			
			editExpired=true;
			
		}
		
	}

	
	public String buildJSON() {

		checkAndBuildVer2();
		//checkAndBuild();
		
		Gson gson = new GsonBuilder()
			.disableHtmlEscaping()
			.excludeFieldsWithoutExposeAnnotation()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting()
			.serializeNulls()
			.create();

		return gson.toJson(this);
	}

}
