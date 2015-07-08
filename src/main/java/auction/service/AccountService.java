package auction.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
import auction.json.TradePoolAndFollowByAccountJson;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	static final Logger logger = Logger.getLogger(AccountService.class);
	
	public List<TradePoolAndFollowByAccountJson> getTradePoolAndFollowByAccount(Principal principal) {

		BasicConfigurator.configure();
		logger.info("!!!!");
		
		if(principal == null)
			return null;

		User user = userRepository.findOneByName(principal.getName());
		Item item;
		StringBuilder sBuilder = new StringBuilder();
	
		List<TradePoolAndFollowByAccountJson> tpFoAccJs = new ArrayList<TradePoolAndFollowByAccountJson>();
		
		List<TradePool> tradePools = tradePoolRepository.findByUser(user);
	
		List<Item> itemsTp = new ArrayList<Item>();
		
		
		for (TradePool tradePool : tradePools) {
		
			item=tradePool.getItem();
			
			if ((item.isActive() == true) && (itemsTp.contains(item) == false)){
							
				sBuilder.setLength(0);
				
				itemsTp.add(item);
				
				tpFoAccJs.add(new TradePoolAndFollowByAccountJson("Trade", item.getName(), sBuilder.append("item-").append(item.getId()).toString()));
			}
			
		}
		
	
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByUserAndFollowTrue(user);
	
		for (UserItemDetail userItemDetail : userItemDetails) {

			item = userItemDetail.getItem();

			if ((item.isActive()) && (itemsTp.contains(item) == false)) {
				
				sBuilder.setLength(0);
				
				tpFoAccJs.add(new TradePoolAndFollowByAccountJson("Follow", item.getName(), sBuilder.append("item-").append(item.getId()).toString()));
			}
				
		}

		return tpFoAccJs;

	}

	public String getTradePoolAndFollowByAccountJson(Principal principal) {

		
		Gson gson = new GsonBuilder()
			.disableHtmlEscaping()
			.excludeFieldsWithoutExposeAnnotation()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting()
			.serializeNulls()
			.create();

		return gson.toJson(getTradePoolAndFollowByAccount(principal));
	}

}
