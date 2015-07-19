package auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.Role;
import auction.entity.User;
import auction.entity.UserDetail;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.RoleRepository;
import auction.repository.UserDetailRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserItemDetailRepository userItemDetailRepository;
	
	
	public User getOne(int id) {
		return userRepository.findOne(id);
	}

	public User getOne(String name) {
		return userRepository.findOneByName(name);
	}

	public void save(User user) {
		
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
		
		UserDetail userDetail = new UserDetail();
		User tempUser = userRepository.findOneByName(user.getName());
		userDetail.setId(tempUser.getId());
		
		userDetailRepository.save(userDetail);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public boolean isOwner(String userName, int itemId) {
		
		User user = userRepository.findOneByName(userName);
		Item item = itemRepository.findOne(itemId);
		
		UserItemDetail userItemDetail = userItemDetailRepository.findOneByUserAndItemAndPublishTrue(user,item);
	
		if (null != userItemDetail)
			return true; 
		else
			return false;
	}

}
