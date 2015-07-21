package auction.service;

import java.security.Principal;
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
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	public void save(Item item, Principal principal) {

		item.setActive(true);
		itemRepository.save(item);

		UserItemDetail userItemDetail = new UserItemDetail();
		userItemDetail.setItem(item);
		userItemDetail.setUser(userRepository.findOneByName(principal.getName()));
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

	public String getTradePoolByItemJson(Principal principal, int id) {

				
		List<TradePoolByItemJson> tpJs = new ArrayList<TradePoolByItemJson>();
		List<TradePool> tradePools = tradePoolRepository.findByItemOrderByAmountDesc(itemRepository.findOne(id));

		for (TradePool tradePool : tradePools) {

			TradePoolByItemJson tpJ = new TradePoolByItemJson(tradePool.getUser().getName(), tradePool.getAmount(), DateTimeUtils.getDateAsString(tradePool.getDate()),
					DateTimeUtils.getTimeAsString(tradePool.getDate()));

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

	public String getNewItemId(Principal principal) {
		
		Item item = new Item();
		User user = userRepository.findOneByName(principal.getName());
		item.setName("View lot detail");
		
		itemRepository.save(item);
		
		UserItemDetail userItemDetail = new UserItemDetail();
		userItemDetail.setUser(user);
		userItemDetail.setItem(item);
		userItemDetail.setPublish(true);
		userItemDetail.setPreActive(true); 
		
		userItemDetailRepository.save(userItemDetail);
		
		userItemDetail = userItemDetailRepository.findByUserAndItemAndPreActiveTrue(user,item);
		
		item = userItemDetail.getItem();
		
		return Integer.toString(item.getId());
	}
}
