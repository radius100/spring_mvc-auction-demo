package auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Transactional
@Service
public class ItemUserDetailService {

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	UserRepository userRepository;

	public User findItemUserPublish(Item item) {

		UserItemDetail userItemDetail = userItemDetailRepository.findByItemAndPublishTrue(item);

		User user = userItemDetail.getUser();

		return user;

	}

	public List<User> findItemUsersFollow(Item item) {

		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByItemAndFollowTrue(item);

		List<User> users = new ArrayList<User>();

		for (UserItemDetail userItemDetail : userItemDetails) 
			users.add(userItemDetail.getUser());
		
		return users;

	}
}
