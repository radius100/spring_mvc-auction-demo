package auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.json.TradePoolByItemJson;
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
	
	@Autowired
	ItemDetailBuilder itemDetailBuilder;

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

		item = DateTimeUtils.createItemDateMessage4IndexJsp(item);

		return item;
	}

	public Item getOneWithTradePoolAndUserItemDetailCore(Item item) {

		List<TradePool> tradePools;
		List<UserItemDetail> userItemDetails;

		tradePools = tradePoolRepository.findByItem(item);
		userItemDetails = userItemDetailRepository.findByItem(item);

		for (TradePool tradePool : tradePools) {

			tradePool.setMessageDate(DateTimeUtils.getDateAsString(tradePool.getDate()));
			tradePool.setMessageTime(DateTimeUtils.getTimeAsString(tradePool.getDate()));
		}

		item.setTradePools(tradePools);
		//item.setUserItemDetails(userItemDetails);

		return item;
	}

	public Item getOne(String userName, int id) {

		//Item item = itemRepository.findOne(id);
		
		Item item = itemDetailBuilder.getOne(id).build();
	
		User user = null;

		if (null != userName)
			user = userRepository.findOneByName(userName);

		item = getOneCore(item, user);
		item = getOneWithTradePoolAndUserItemDetailCore(item);

		return item;
	}

	public Item getOne(int id) {
		
		return itemRepository.findOne(id);
	}

	public List<Item> getAll(String name) {

		User user = userRepository.findOneByName(name);

		List<Item> items = itemRepository.findItemByActiveTrueAndSellFalseAndBlockFalse();
		// List<Item> items = itemRepository.findItemBySellFalseAndBlockFalse();

		for (Item item : items)
			item = getOneCore(item, user);

		return items;
	}

	public List<Item> getAll() {

		List<Item> items = itemRepository.findItemByActiveTrueAndSellFalseAndBlockFalse();
		// List<Item> items = itemRepository.findItemBySellFalseAndBlockFalse();

		for (Item item : items)
			item = getOneCore(item, null);

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

	public String getTradePoolByItemJson(int id) {
		
		List<TradePoolByItemJson> tpJs = new ArrayList<TradePoolByItemJson>();
		List<TradePool> tradePools = tradePoolRepository.findByItem(itemRepository.findOne(id));
		
		for(TradePool tradePool : tradePools) {
			
			TradePoolByItemJson tpJ = new TradePoolByItemJson(
							tradePool.getUser().getName(),
							tradePool.getAmount(),
							DateTimeUtils.getDateAsString(tradePool.getDate()),
							DateTimeUtils.getTimeAsString(tradePool.getDate())
							);
			
			tpJs.add(tpJ);
		}
		
		Gson gson = new GsonBuilder()
        .disableHtmlEscaping()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .serializeNulls()
        .create();
		
		return gson.toJson(tpJs);
	}

}
