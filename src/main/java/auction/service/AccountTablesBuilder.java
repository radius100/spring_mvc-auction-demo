package auction.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.BasicConfigurator;
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
import auction.json.AccountTablesJson;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class AccountTablesBuilder {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	static final Logger logger = Logger.getLogger(AccountService.class);
	
	private User user;
	private Item item;
	private StringBuilder sBuilder;

	private List<AccountTablesJson> accountTables;
	private List<TradePool> tradePools;
	private List<Item> itemsTp;
	private List<Item> hideItems;
	private List<Item> expandItems;
	private List<UserItemDetail> hidesUID;
	private List<UserItemDetail> collapsesUID;
	private List<UserItemDetail> followersUID;
	
	boolean err_flag=false;
	
	public AccountTablesBuilder init(Principal principal){
		
		//BasicConfigurator.configure();
		//logger.info("!!!!");
		
		if(principal == null) {
			
			err_flag=true;
			return null;
		}

		user = userRepository.findOneByName(principal.getName());
		tradePools = tradePoolRepository.findByUser(user);
		
		hidesUID = userItemDetailRepository.findByUserAndHideTrue(user);
		collapsesUID = userItemDetailRepository.findByUserAndCollapseTrue(user);
		followersUID = userItemDetailRepository.findByUserAndFollowTrue(user);
		
		sBuilder = new StringBuilder();
		
		accountTables = new ArrayList<AccountTablesJson>();
		
		itemsTp = new ArrayList<Item>();
		
		hideItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : hidesUID)
			hideItems.add(uIDetail.getItem());
	
		expandItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : collapsesUID)
			expandItems.add(uIDetail.getItem());
	
		
		return this;
	}
	
	public AccountTablesBuilder getTradePools(){

		boolean expandBool;

		for (TradePool tradePool : tradePools) {
			
			item=tradePool.getItem();
			
			if( hideItems.contains(item) == true ){
				itemsTp.add(item);
				
				continue;
			}
				
			if ((item.isActive() == true) && (itemsTp.contains(item) == false)){
							
				sBuilder.setLength(0);
				
				itemsTp.add(item);
				
				if(expandItems.contains(item) == true)
					expandBool=true;
				else
					expandBool=false;

				
				accountTables.add(new AccountTablesJson("Trade", 
						item.getName(), 
						sBuilder.append("item-").append(item.getId()).toString(),
						expandBool));
			}
			
		}

		return this;
	}

	public AccountTablesBuilder getFollowers(){
	
		boolean expandBool;
		
		for (UserItemDetail uIDetail : followersUID) {

			item = uIDetail.getItem();
			
			if ((item.isActive()) && (itemsTp.contains(item) == false)) {
				
				sBuilder.setLength(0);
				
				if(expandItems.contains(item) == true)
					expandBool=true;
				else
					expandBool=false;
				
				accountTables.add(new AccountTablesJson("Follow", 
						item.getName(), 
						sBuilder.append("item-").append(item.getId()).toString(), 
						expandBool));
			}
				
		}
		
		return this;
	}	
	
	
	public List<AccountTablesJson> build() {

		return accountTables;

	}

	
	public String buildJSON() {

		Gson gson = new GsonBuilder()
			.disableHtmlEscaping()
			.excludeFieldsWithoutExposeAnnotation()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting()
			.serializeNulls()
			.create();

		return gson.toJson(accountTables);
	}
	
}
