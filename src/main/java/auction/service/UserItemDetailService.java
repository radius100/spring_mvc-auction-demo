package auction.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Transactional
@Service
public class UserItemDetailService {

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserRepository userRepository;

	
	public String toggleFollow(Principal principal, int id) {
		
		if(principal == null)
			return "Login first";
		
		Item item = itemRepository.findOne(id);
		User user = userRepository.findOneByName(principal.getName());
		
		UserItemDetail userItemDetail = userItemDetailRepository.findByItemAndUserAndFollowTrue(item,user);
		
		if(userItemDetail == null){
			
			UserItemDetail uID = new UserItemDetail();
			uID.setUser(user);
			uID.setItem(item);
			uID.setFollow(true);
			userItemDetailRepository.save(uID);

			return "Follow";
		}
		else{
			
			userItemDetailRepository.delete(userItemDetail);
			
			return "Unfollow";
		}
		
	}
	
	
	
	/*
	public List<Item> findUserItemsPublish(User user) {

		List<UserItemDetail> userItemDetails = userItemDetailRepository.findItemsByUserAndPublishTrue(user);

		List<Item> items = new ArrayList<Item>();

		for (UserItemDetail userItemDetail : userItemDetails) 
			items.add(userItemDetail.getItem());
		
		return items;

	}

	public List<Item> findUserItemsFollow(User user) {

		List<UserItemDetail> userItemDetails = userItemDetailRepository.findItemsByUserAndFollowTrue(user);

		List<Item> items = new ArrayList<Item>();

		for (UserItemDetail userItemDetail : userItemDetails) 
			items.add(userItemDetail.getItem());
		
		return items;

	}*/
}
