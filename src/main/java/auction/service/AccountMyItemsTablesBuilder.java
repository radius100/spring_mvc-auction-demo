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
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.json.AccountTablesJson;
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

	private List<AccountTablesJson> accountTables;
	private List<Item> collapseItems;
	private List<UserItemDetail> collapsesUID;
	
	private List<Item> publishItems;
	private List<UserItemDetail> publishByPrincipalUID;
	//private List<User> followers;
	
	//проверить на анонима!!!
	boolean err_flag=false;

	/*
	 * 1. «апрет на ставки по своим лотам
	 * 2. ѕолучить List<Item> items по всем лотам published principal
	 * 3. 
	 * 
	 * 
	 */
	
	public AccountMyItemsTablesBuilder init(Principal principal){
		
		if(principal == null) {
			
			err_flag=true;
			return null;
		}

		user = userRepository.findOneByName(principal.getName());
		
		publishByPrincipalUID = userItemDetailRepository.findByUserAndPublishTrue(user);
		collapsesUID = userItemDetailRepository.findByUserAndCollapseTrue(user);
		
		accountTables = new ArrayList<AccountTablesJson>();
		
		publishItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : publishByPrincipalUID)
			publishItems.add(uIDetail.getItem());
		
		collapseItems = new ArrayList<Item>();
		for(UserItemDetail uIDetail : collapsesUID)
			collapseItems.add(uIDetail.getItem());
		
		sBuilder = new StringBuilder();
		
		return this;
	}
	
	public AccountMyItemsTablesBuilder getTradePoolsActive(){

		boolean expandBool;

		for (Item item : publishItems) {
			
			if ( item.isActive() == true ){
							
				sBuilder.setLength(0);
				
				if(collapseItems.contains(item) == true)
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


	public List<AccountTablesJson> build() {
		
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
