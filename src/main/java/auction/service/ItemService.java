package auction.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	@Autowired
	TradePoolService tradePoolService;

	public List<Item> findAllCore(List<Item> items, User user) {

		boolean isFollow;
		boolean isTrade;

		for (Item item : items) {

			item.setFollowersCount(0);
			item.setTradersCount(0);
			item.setCurrentAmount(0);
			isFollow = false;
			isTrade = false;
			
			item.setFollowersCount(userItemDetailRepository.countUserDistinctByItemAndFollowTrue(item));
			item.setTradersCount(tradePoolRepository.countDistinctUserByItem(item));
			item.setCurrentAmount(tradePoolService.findCurrentAmount(item));

			if (null != user) {

				UserItemDetail userItemDetail = userItemDetailRepository.findOneByFollowTrueAndUserAndItem(user, item);
				if (null != userItemDetail)
					isFollow = true;
				item.setFollowedByCurrentUser(isFollow);

				if (tradePoolRepository.countByUserAndItem(user, item) > 0)
					isTrade = true;
				item.setTradeedByCurrentUser(isTrade);
			
			}
			
			DateTime dateStart   = new DateTime(item.getStartDate());
			DateTime dateFinish  = new DateTime(item.getFinishDate());
  			DateTime dateCurrent = new DateTime();
  			
  			dateCurrent=dateCurrent.minusMinutes(25);
  			//dateStart=dateStart.minusMinutes(35);
  			dateFinish=dateFinish.plusDays(2);
  			
  			if( dateCurrent.isBefore(dateStart) ){
  		 		
  				Duration duration1 = new Duration(dateCurrent, dateStart);
  				long d1 = duration1.getStandardDays();
  				long h1 = duration1.getStandardHours();
  			  				
  				if( d1 >= 1 ){
  					item.setDateMessage(1);
  					item.setDateValue(d1);
  				}
  				else if( h1 >= 1 && h1 <= 24){
  					item.setDateMessage(2);
  					item.setDateValue(h1);
  				}
  					
  				else if( h1 < 1 )
  					item.setDateMessage(3);
  					
  			}
  			else if( dateCurrent.isBefore(dateFinish) && dateCurrent.isAfter(dateStart) ){
  				
  				Duration duration2 = new Duration(dateCurrent, dateFinish);
  				long d2 = duration2.getStandardDays();
  				long h2 = duration2.getStandardHours();
  			  	
  				if( d2 >= 1 ){
  					item.setDateMessage(4);
  					item.setDateValue(d2);
  				}
  					
  				else if( h2 >= 1 && h2 <= 24){
  					item.setDateMessage(5);
  					item.setDateValue(h2);
  				}
  					
  				else if( h2 < 1 ) 
  					item.setDateMessage(6);
  			}
  		}

		return items;
	}

	public List<Item> findAll(String name) {

		User user = userRepository.getOneByName(name);

		List<Item> items = itemRepository.findItemByActiveTrueAndSellFalseAndBlockFalse();

		return findAllCore(items, user);
	}

	public Item findOne(int id) {
		return itemRepository.findOne(id);
	}

	public List<Item> findAll() {

		return itemRepository.findAll();
	}
}
