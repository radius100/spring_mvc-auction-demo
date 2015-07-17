package auction.service;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
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


	public String setHide(Principal principal, int id) {
		
		if(principal == null)
			return "fail";
		
		User user = userRepository.findOneByName(principal.getName());
		Item item = itemRepository.findOne(id);
		
		if(userItemDetailRepository.findByUserAndItemAndHideTrue(user,item) == null){
			
			UserItemDetail userItemDetail = new UserItemDetail();
			userItemDetail.setItem(item);
			userItemDetail.setUser(user);
			userItemDetail.setHide(true);
			userItemDetailRepository.save(userItemDetail);
			
			return "ok";
			
		}
		else
			return "fail";	
			
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
			
			//return "setExpand";
			return "Collapsed";
			
		}
		else {
			
			userItemDetailRepository.delete(userItemDetail);
			
			//return "setCollapse";
			return "Expanded";
		}
			
		
	}

}
