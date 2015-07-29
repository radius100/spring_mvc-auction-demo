package auction.json;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

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
	
	DateTime now = new DateTime();
	DateTime publish;
	DateTime start;
	DateTime finish;
	
	private boolean editExpired;
	private Locale locale;
	private Item item;
	private String parseString = "d MMM yyyy HH:mm";
	
	String publishDateInputBox; 
	String startDateInputBox; 
	String finishDateInputBox;
	
	
		
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
		publishDateMin = start.plusDays(1).toString(parseString, locale);
		publishDateMax = start.plusMonths(1).toString(parseString, locale);
		
	}

	
	public void checkAndBuild() {
		
		if( item.getPublishDate() == null ){
			
			publish = new DateTime(now);
			start = new DateTime(now);
			finish = new DateTime(start.plusDays(3));
			
			fillPublishDateMinMaxDate(now);
			
			fillStartDateMinMaxDate(start);
			
			fillFinishDateMinMaxDate(finish);
			
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
			
			fillPublishDateMinMaxDate( new DateTime(item.getPublishDate()) );
			
			fillStartDateMinMaxDate( new DateTime(item.getStartDate()) );
			
			fillFinishDateMinMaxDate( new DateTime(item.getFinishDate()) );
			
			editExpired=true;
			
		}
		
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
