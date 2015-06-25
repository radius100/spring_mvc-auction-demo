package auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;
import auction.utils.DateTimeUtils;

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
	
	
	public Item getOneCore(Item item, User user) {

		item.setFollowersCount(0);
		item.setTradersCount(0);
		item.setCurrentAmount(0);
		boolean isFollow = false;
		boolean isTrade = false;

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
		
		item=DateTimeUtils.createItemDateMessage4IndexJsp(item);

		return item;
	}

	
	public Item getOneWithTradePoolAndUserItemDetailCore(Item item) {

		List<TradePool> tradePools;
		List<UserItemDetail> userItemDetails;
		
		tradePools=tradePoolRepository.findByItem(item);
		userItemDetails=userItemDetailRepository.findByItem(item);
		
		for(TradePool tradePool : tradePools){
			
			tradePool.setMessageDate(DateTimeUtils.getDateAsString(tradePool));
			tradePool.setMessageTime(DateTimeUtils.getTimeAsString(tradePool));
		}
		
		item.setTradePools(tradePools);
		item.setUserItemDetails(userItemDetails);
		
		return item;
	}

	public Item getOne(String userName, int id) {
		
		Item item = itemRepository.findOne(id);
		User user = null;
		
		if( null != userName ) 
			user = userRepository.findOneByName(userName);
				
		item=getOneCore(item, user);
		item=getOneWithTradePoolAndUserItemDetailCore(item);
		
		return item;
	}
	
	public List<Item> getAll(String name) {

		User user = userRepository.findOneByName(name);

		List<Item> items = itemRepository.findItemByActiveTrueAndSellFalseAndBlockFalse();
		//List<Item> items = itemRepository.findItemBySellFalseAndBlockFalse();

		for (Item item : items)
			item = getOneCore(item, user);

		return items;
	}

	public void save(Item item, User user) {

		item.setActive(true);
		itemRepository.save(item);
		
		UserItemDetail userItemDetail = new UserItemDetail();
		userItemDetail.setItem(item);
		userItemDetail.setUser(user);
		userItemDetail.setPublish(true);
		userItemDetailRepository.save(userItemDetail);		
	}

	public void update(Item itemToSave, int itemId) {
		
		Item item = itemRepository.findOne(itemId);
		
		item.setId(itemId);
		item.setName(itemToSave.getName());
		item.setDescr(itemToSave.getDescr());
		item.setFullDescr(itemToSave.getFullDescr());
		item.setStartAmount(itemToSave.getStartAmount());
		item.setPublishDate(itemToSave.getPublishDate());
		item.setStartDate(itemToSave.getStartDate());
		item.setFinishDate(itemToSave.getFinishDate());
		
		itemRepository.save(item);
	}

/*
	public List<Item> getAll() {

		return itemRepository.findAll();
	}
*/	
}
