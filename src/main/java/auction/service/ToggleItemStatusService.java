package auction.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import auction.constant.Constants;
import auction.entity.Item;
import auction.repository.ItemRepository;

@Service
@Transactional
public class ToggleItemStatusService {

	@Autowired
	ItemRepository itemRepository;
	
	//@Scheduled(cron="00 * * * * *")
	public void checkItemStatusAndUpdate(){
		
		List<Item> items = itemRepository.findByArchiveFalse();
		
		Date now = new Date();
		Date publishDate;
		Date startDate;
		Date finishDate;
		
		String publishDateString;
		String startDateString;
		String finishDateString;
		String nowDateString;
		
		boolean updateFlag;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATETIMEFORMATTER_STRING);
		
		nowDateString = simpleDateFormat.format(now);
		
		for(Item item : items){

			publishDate = item.getPublishDate();
			startDate   = item.getStartDate();
			finishDate  = item.getFinishDate();
			
			if( publishDate == null || startDate == null || finishDate == null )
				continue;
			
			updateFlag = false;
			
			publishDateString = simpleDateFormat.format(publishDate);
			startDateString   = simpleDateFormat.format(startDate);
			finishDateString  = simpleDateFormat.format(finishDate);
			
			if( nowDateString.equals(publishDateString) ){
				item.setPreTrading(true);
				item.setActive(true);
				
				item.setTrading(false);
				item.setArchive(false);
				updateFlag=true;
			}
			
			else if( nowDateString.equals(startDateString) ){
				item.setTrading(true);
				item.setPreTrading(false);
				
				item.setArchive(false);
				item.setActive(true);
				updateFlag=true;
			}
			
			else if( nowDateString.equals(finishDateString) ){
				item.setArchive(true);
				item.setActive(false);
				item.setTrading(false);
				
				item.setPreTrading(false);
				updateFlag=true;
			}
			
			if( updateFlag )
				itemRepository.save(item);
			
		}
	}
	
}
