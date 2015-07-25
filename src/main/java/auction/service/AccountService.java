package auction.service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.builder.AccountMonitorTablesBuilder;
import auction.builder.AccountMyItemsTablesBuilder;
import auction.builder.TradePoolBuilder;
import auction.builder.UserItemDetailBuilder;
import auction.builder.ItemDetailBuilder;
import auction.entity.Item;
import auction.entity.User;
import auction.json.AccountTables;
import auction.repository.UserRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TradePoolBuilder tradePoolBuilder;

	@Autowired
	ItemDetailBuilder itemDetailBuilder;

	@Autowired
	UserItemDetailBuilder userItemDetailBuilder;
	
	@Autowired
	private AccountMonitorTablesBuilder accountMonitorTablesBuilder;
	
	@Autowired
	private AccountMyItemsTablesBuilder accountMyItemsTablesBuilder;

	
	public List<Item> getMyItemSettings(Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
	
		List<Item> items = 
				userItemDetailBuilder
		    		.setUser(user)
		    		.setFindByUserAndPublishTrue()
		    		.getItemList();
	
		
		for( Item item : items )
			item = 
				itemDetailBuilder
					.setOne(item)
					.setPrincipal(user)
					.checkDeletable()
					.setIsHide()
					.setIsCollapse()
					.build();
		
		return items;
	}
	
	public Set<Item> getTradingMonitorSettings(Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
		Set<Item> items = new HashSet<Item>();

		
		items.addAll( tradePoolBuilder
		 				.setUser(user)
		 				.setFindByUser()
		 				.getItemList()
				    );
		  
		
		items.addAll( userItemDetailBuilder
				    	.setUser(user)
				    	.setFindByUserAndFollowTrue()
				    	.getItemList()
				 	);
				  
		
		for(Item item : items)
			item = itemDetailBuilder
					.setOne(item)
					.setPrincipal(user)
					.setIsHide()
					.setIsCollapse()
					.setIsFollowByPrincipal()
					.build();
			
		return items;
	}
	
	public List<AccountTables> getAccountMonitorTables(Principal principal){
		
		return accountMonitorTablesBuilder
					.init(principal)
					.getTradePools()
					.getFollowers()
					.build();
		
	}
	
	public List<AccountTables> getAccountMyItemsTables(Principal principal){
		
		return accountMyItemsTablesBuilder
					.init(principal)
					.getTradePoolsActive()
					.build();
		
	}
	
	public String getAccountMonitorTablesJSON(Principal principal){
		
		return accountMonitorTablesBuilder
					.init(principal)
					.getTradePools()
					.getFollowers()
					.buildJSON();
		
	}
	
	public String getAccountMyItemsTablesJSON(Principal principal){
		
		return accountMyItemsTablesBuilder
					.init(principal)
					.getTradePoolsActive()
					.buildJSON();
		
	}
	

}
