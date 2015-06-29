package auction.service;

import java.security.Principal;
import java.util.ArrayList;
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
public class ItemDetailBuilder {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	private Item item;

	private User user;

	private List<Item> items;

	private List<User> users;

	
	public Item build() {

		return item;
	}


	//TO-DO
	public ItemDetailBuilder getAll() {

		
		return this;
	}

	
	public ItemDetailBuilder getOne(int id) {

		item = itemRepository.findOne(id);
		return this;
	}

	public ItemDetailBuilder getOne(Item item) {

		this.item = item;
		return this;
	}

	public ItemDetailBuilder setPrincipal(Principal principal) {

		if(principal != null)
			user = userRepository.findOneByName(principal.getName());
		
		return this;
	}
	
	public ItemDetailBuilder getFollowersCount() {

		item.setFollowersCount(userItemDetailRepository.countUserDistinctByItemAndFollowTrue(item));
		return this;
	}

	public ItemDetailBuilder getTradersCount() {

		item.setTradersCount(tradePoolRepository.countDistinctUserByItem(item));
		return this;
	}

	public ItemDetailBuilder getCurrentAmount() {

		TradePool tradePool = tradePoolRepository.findFirstByItemOrderByAmountDesc(item);

		if (tradePool != null)
			item.setCurrentAmount(tradePool.getAmount());
		else
			item.setCurrentAmount(-1);

		return this;
	}

	public ItemDetailBuilder getIsFollow() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByFollowTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setFollowedByCurrentUser(true);
			else
				item.setFollowedByCurrentUser(false);
		}
		return this;
	}

	public ItemDetailBuilder getIsPublish() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByPublishTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setPublishedByCurrentUser(true);
			else
				item.setPublishedByCurrentUser(false);

		}
		return this;
	}

	public ItemDetailBuilder getIsBuy() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByBuyTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setBuyByCurrentUser(true);
			else
				item.setBuyByCurrentUser(false);

		}
		return this;
	}

	public ItemDetailBuilder getIsTrade() {

		if (tradePoolRepository.countByUserAndItem(user, item) > 0)
			item.setTradeedByCurrentUser(true);
		item.setTradeedByCurrentUser(false);

		return this;
	}

	public ItemDetailBuilder getTradePool() {

		List<TradePool> tradePools;

		tradePools = tradePoolRepository.findByItem(item);

		for (TradePool tradePool : tradePools) {

			tradePool.setMessageDate(DateTimeUtils.getDateAsString(tradePool.getDate()));
			tradePool.setMessageTime(DateTimeUtils.getTimeAsString(tradePool.getDate()));
		}

		item.setTradePools(tradePools);

		return this;
	}

	//переделать List в Set чтоб повторени€ исключить
	public ItemDetailBuilder getTraders() {

		List<User> users = new ArrayList<User>();
		List<TradePool> tradePools;

		tradePools = tradePoolRepository.findUserDistinctByItem(item);

		for (TradePool tradePool : tradePools)
			users.add(tradePool.getUser());
		
		item.setTraders(users);

		return this;
	}

	
	public ItemDetailBuilder createItemDateMessage4IndexJsp() {

		DateTimeUtils.createItemDateMessage4IndexJsp(item);
		return this;
	}

	public ItemDetailBuilder getFollowers() {

		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByItemAndFollowTrue(item);
		
		List <User> users = new ArrayList<User>();
		for(UserItemDetail userItemDetail : userItemDetails)
			users.add(userItemDetail.getUser());
		
		item.setFollowers(users);
		
		return this;
	}

	//ѕочему сразу тип User не возвращает??
	public ItemDetailBuilder getPublisher() {

		 UserItemDetail userItemDetail = userItemDetailRepository.findOneByItemAndPublishTrue(item);
		// userItemDetailRepository.findUserByItemAndPublishTrue(item);
		
		item.setPublisher(userItemDetail.getUser());
		return this;
	}
}
