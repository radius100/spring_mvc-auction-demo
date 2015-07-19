package auction.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Autowired
	ItemDetailBuilder itemDetailBuilder;

	
	public String toggleHide(Principal principal, int id) {
		
		if(principal == null)
			return "fail";
		
		User user = userRepository.findOneByName(principal.getName());
		Item item = itemRepository.findOne(id);
		UserItemDetail userItemDetail = userItemDetailRepository.findByUserAndItemAndHideTrue(user,item);
		
		
		if( userItemDetail == null){
			
			UserItemDetail uIDetail = new UserItemDetail();
			uIDetail.setItem(item);
			uIDetail.setUser(user);
			uIDetail.setHide(true);
			userItemDetailRepository.save(uIDetail);
			
			return "Hidden";
			
		}
		else {
			
			userItemDetailRepository.delete(userItemDetail);
			
			return "Show";
		}
			
	}

	public String toggleCollapse(Principal principal, int id) {
		
		if( principal == null )
			return "fail";
		
		User user = userRepository.findOneByName(principal.getName());
		Item item = itemRepository.findOne(id);
		UserItemDetail userItemDetail = userItemDetailRepository.findByUserAndItemAndCollapseTrue(user,item); 
		
		if( userItemDetail == null ){
			
			UserItemDetail uIDetail = new UserItemDetail();
			uIDetail.setItem(item);
			uIDetail.setUser(user);
			uIDetail.setCollapse(true);
			userItemDetailRepository.save(uIDetail);
			
			return "Collapsed";
			
		}
		else {

			userItemDetailRepository.delete(userItemDetail);
			
			return "Expanded";
		}
		
	}

	public List<Item> getMyItemSettings(Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
		List<Item> items = new ArrayList<Item>();
		Item item;
		
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByUserAndPublishTrue(user);
		
		for(UserItemDetail userItemDetail : userItemDetails){
			
			item = userItemDetail.getItem();
			
			item = itemDetailBuilder
					.getOne(item)
					.setPrincipal(principal)
					.getIsHide()
					.getIsCollapse()
					.build();
			
			items.add(item);
		}
		
		return items;
	}
	
	public Set<Item> getTradingMonitorSettings(Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
		Set<Item> items = new HashSet<Item>();
		List<TradePool> tradePools = tradePoolRepository.findByUser(user);
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByUserAndFollowTrue(user);
		
		for(TradePool tradePool : tradePools)
			items.add(tradePool.getItem());

		for(UserItemDetail userItemDetail : userItemDetails)
			items.add(userItemDetail.getItem());
		
		for(Item item : items)
			item = itemDetailBuilder
					.getOne(item)
					.setPrincipal(user)
					.getIsHide()
					.getIsCollapse()
					.getIsFollowByPrincipal()
					.build();
			
		return items;
	}

}
