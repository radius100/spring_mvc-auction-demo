package auction.builder;

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
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.json.AccountTables;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class AccountMyItemsTablesBuilder {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	private User user;
	private StringBuilder sBuilder;

	private List<AccountTables> accountTables;
	
	private List<Item> collapseItems;
	private List<UserItemDetail> collapsesUID;
	
	private List<Item> publishItems;
	private List<UserItemDetail> publishByPrincipalUID;
	
	private List<Item> hideItems;
	private List<UserItemDetail> hidesUID;
	
	
	//проверить на анонима!!!
	boolean err_flag=false;
	
	
	public AccountMyItemsTablesBuilder init(Principal principal){
		
		if(principal == null) {
			
			err_flag=true;
			return null;
		}

		user = userRepository.findOneByName(principal.getName());
		
		publishByPrincipalUID = userItemDetailRepository.findByUserAndPublishTrue(user);
		collapsesUID = userItemDetailRepository.findByUserAndCollapseTrue(user);
		hidesUID = userItemDetailRepository.findByUserAndHideTrue(user);
		
		accountTables = new ArrayList<AccountTables>();
		
		publishItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : publishByPrincipalUID)
			publishItems.add(uIDetail.getItem());
		
		collapseItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : collapsesUID)
			collapseItems.add(uIDetail.getItem());
		
		hideItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : hidesUID)
			hideItems.add(uIDetail.getItem());
		
		sBuilder = new StringBuilder();
		
		return this;
	}
	
	public AccountMyItemsTablesBuilder getTradePoolsActive(){

		boolean expandBool;
		boolean hideBool;

		for (Item item : publishItems) {
			
			if ( item.isActive() == true ){
							
				sBuilder.setLength(0);
				expandBool=false;
				hideBool=false;
				
				if(collapseItems.contains(item) == true)
					expandBool=true;
				
				if(hideItems.contains(item) == true)
					hideBool=true;
				
				accountTables.add(new AccountTables("Trade", 
						item.getName(), 
						sBuilder.append("item-").append(item.getId()).toString(),
						expandBool,hideBool));
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
