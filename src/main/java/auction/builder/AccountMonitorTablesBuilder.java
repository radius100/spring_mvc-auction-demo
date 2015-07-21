package auction.builder;

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
import auction.json.AccountTables;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class AccountMonitorTablesBuilder {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	static final Logger logger = Logger.getLogger(AccountMonitorTablesBuilder.class);
	
	private User user;
	private Item item;
	private StringBuilder sBuilder;

	private List<AccountTables> accountTables;
	private List<TradePool> tradePools;
	private List<Item> items;
	private List<Item> hideItems;
	private List<Item> collapseItems;
	private List<UserItemDetail> hidesUID;
	private List<UserItemDetail> collapsesUID;
	private List<UserItemDetail> followersUID;

	//проверить на анонима!!!
	boolean err_flag=false;
	boolean expandBool;
	boolean hideBool;

	
	public AccountMonitorTablesBuilder init(Principal principal){
		
		//BasicConfigurator.configure();
		//logger.info("!!!!");
		
		if( principal == null ) 
			return null;
		
		
		sBuilder 		 = new StringBuilder();

		accountTables	 = new ArrayList<AccountTables>();
		
		hideItems 		 = new ArrayList<Item>();
		
		collapseItems 	 = new ArrayList<Item>();

		items			 = new ArrayList<Item>();

		
		user 		 =	userRepository.findOneByName(principal.getName());
		tradePools	 =	tradePoolRepository.findByUser(user);
		
		hidesUID	 =	userItemDetailRepository.findByUserAndHideTrue(user);
		collapsesUID =	userItemDetailRepository.findByUserAndCollapseTrue(user);
		followersUID =	userItemDetailRepository.findByUserAndFollowTrue(user);
		
		for(UserItemDetail uIDetail : hidesUID) 
			hideItems.add(uIDetail.getItem());
	
		for(UserItemDetail uIDetail : collapsesUID)
			collapseItems.add(uIDetail.getItem());
		
		return this;
	}
	
	public AccountMonitorTablesBuilder getTradePools(){

		for (TradePool tradePool : tradePools) {
			
			item=tradePool.getItem();
			
			if ((item.isActive() == true) && (items.contains(item) == false)){
							
				sBuilder.setLength(0);
				
				items.add(item);
				
				if(collapseItems.contains(item) == true)
					expandBool=true;
				else
					expandBool=false;

				if(hideItems.contains(item) == true)
					hideBool=true;
				else
					hideBool=false;

				
				accountTables.add(new AccountTables("Trade", 
						item.getName(), 
						sBuilder.append("item-").append(item.getId()).toString(),
						expandBool, hideBool));
			}
			
		}

		return this;
	}

	public AccountMonitorTablesBuilder getFollowers(){
	
		for (UserItemDetail uIDetail : followersUID) {

			item = uIDetail.getItem();
			
			if ((item.isActive()) && (items.contains(item) == false)) {
				
				sBuilder.setLength(0);
				
				if(collapseItems.contains(item) == true)
					expandBool=true;
				else
					expandBool=false;
				
				if(hideItems.contains(item) == true)
					hideBool=true;
				else
					hideBool=false;

				
				accountTables.add(new AccountTables("Follow", 
						item.getName(), 
						sBuilder.append("item-").append(item.getId()).toString(), 
						expandBool, hideBool));
			}
				
		}
		
		return this;
	}	
	
	
	public List<AccountTables> build() {
		
		//if(err_flag)
		//	return null;
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
