package auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.UserItemDetailRepository;

@Transactional
@Service
public class UserItemDetailService {

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	ItemRepository itemRepository;

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

	}
}
