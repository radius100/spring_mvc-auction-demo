package auction.json;

import java.util.Locale;

import org.joda.time.DateTime;
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
	private String pattern = "dd MM yyyy HH:mm";
	
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

	
	public void checkAndBuild(){
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		formatter.withLocale(locale);
		

    	try {
			
			publish = formatter.parseDateTime(publishDateInputBox);
	
		} catch(Exception e) {
			
			logger.info("catch publishDate");
        	if(item.getPublishDate() != null)
        		publish = new DateTime(item.getPublishDate());
        	else
        		publish = new DateTime(now.plusMinutes(10));
        } 
		
		
		try {
		
			start = formatter.parseDateTime(startDateInputBox);
		
		} catch(Exception e) {
		
			logger.info("catch startDate");
			if(item.getStartDate() != null)
        		start = new DateTime(item.getStartDate());
        	else
        		start = new DateTime(publish.plusDays(1));
        }
		
		
		try {
			
			finish = formatter.parseDateTime(finishDateInputBox);
		
		} catch(Exception e) {
		
			logger.info("catch finishDate");
			if(item.getFinishDate() != null)
        		finish = new DateTime(item.getFinishDate());
        	else
        		finish = new DateTime(start.plusWeeks(1));
        } 
		

		logger.info("!!! " + publish.toString());
		logger.info(start.toString());
		logger.info(finish.toString());
		
		if( publish.isBeforeNow() )
			editExpired=true;
			
		
		else{
			
			if( publish.isAfter(now.plusDays(14)) )
				publish = now.plusDays(14);
			
			if( start.isBefore(publish) )
				start = publish;
			
			if( now.plusDays(30).isBefore(start) )
				start = now.plusDays(30);
			
			if( finish.isBefore(start.plusDays(1)) )
				finish = start.plusDays(1);
			
			if( now.plusDays(31).isBefore(finish) )
				finish = now.plusDays(31);
			
			
			publishDate = publish.toString(pattern, locale);
			publishDateMin = now.toString(pattern, locale);
			publishDateMax = now.plusDays(14).toString(pattern, locale);
			
			startDate = start.toString(pattern, locale);
			startDateMin = publish.toString(pattern, locale);
			startDateMax = now.plusDays(30).toString(pattern, locale);
			
			finishDate = finish.toString(pattern, locale);
			finishDateMin = start.plusDays(1).toString(pattern, locale);
			finishDateMax = now.plusDays(31).toString(pattern, locale);
			
		}	
	
		logger.info("??? " + publish.toString());
		logger.info(start.toString());
		logger.info(finish.toString());
		
		logger.info(Boolean.toString(editExpired));
	}

	
	public String buildJSON() {

		checkAndBuild();
		
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
