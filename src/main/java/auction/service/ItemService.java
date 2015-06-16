package auction.service;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
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
			item.setDateMessage("Soon will be");
			
			item.setFollowersCount(userItemDetailRepository.countUserDistinctByItemAndFollowTrue(item));
			item.setTradersCount(tradePoolRepository.countDistinctUserByItem(item));
			item.setCurrentAmount(tradePoolService.findCurrentAmount(item));
			
			UserItemDetail userItemDetail = userItemDetailRepository.findOneByFollowTrueAndUserAndItem(user, item);
			if( null != userItemDetail ) 
				isFollow = true;
			item.setFollowedByCurrentUser(isFollow);

			if (tradePoolRepository.countByUserAndItem(user, item) > 0)
				isTrade = true;
			item.setTradeedByCurrentUser(isTrade);
			
			/*
			 * dateTemp = new LocalDate (item.getStartDate()); Period period =
			 * new Period(dateTemp, dateCurrent , PeriodType.yearMonthDay());
			 * 
			 * 
			 * if ( date.after(item.getStartDate()) ){ // закрытие торгов по
			 * лоту: finishDate } else if ( date.after(item.getPublishDate()) ){
			 * // открытие торгов по лоту: }
			 */

	}
		
		return items;
	}		
	
	public List<Item> findAll(String name) {

		User user = userRepository.getOneByName(name);

		List<Item> items = itemRepository.findItemByActiveTrueAndSellFalseAndBlockFalse();

		items=findAllCore(items,user);
		
		return items;
}

	public Item findOne(int id) {
		return itemRepository.findOne(id);
	}

	public List<Item> findAll() {

		return itemRepository.findAll();
	}
}
