package auction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.Role;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.RoleRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserDetailRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserItemDetailRepository userItemDetailRepository;
	
	@Autowired
	private TradePoolRepository tradePoolRepository;
	
	@PostConstruct
	public void init(){

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setPassword("admin");
		userAdmin.setEnabled(true);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userRepository.save(userAdmin);
		
		User userTest = new User();
		userTest.setName("test");
		userTest.setPassword("test");
		userTest.setEnabled(true);
		roles.clear();
		roles.add(roleUser);
		userRepository.save(userTest);

		User userTest1 = new User();
		userTest1.setName("test1");
		roles.clear();
		roles.add(roleUser);
		userRepository.save(userTest1);

		Item item1 = new Item();
		item1.setName("Ëîò 1");
		item1.setStartAmount(10);
		item1.setActive(true);
		item1.setSell(false);
		item1.setBlock(false);
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setName("Ëîò 2");
		item2.setStartAmount(10);
		item2.setActive(true);
		item2.setSell(false);
		item2.setBlock(false);
		itemRepository.save(item2);
		
		Item item3 = new Item();
		item3.setName("Ëîò 3");
		item3.setStartAmount(10);
		item3.setActive(true);
		item3.setSell(false);
		item3.setBlock(false);
		itemRepository.save(item3);
		
		UserItemDetail userItemDetail1 = new UserItemDetail();
		userItemDetail1.setUser(userAdmin);
		userItemDetail1.setItem(item1);
		userItemDetail1.setPublish(true);
		userItemDetailRepository.save(userItemDetail1);
		
		UserItemDetail userItemDetail3 = new UserItemDetail();
		userItemDetail3.setUser(userAdmin);
		userItemDetail3.setItem(item2);
		userItemDetail3.setPublish(true);
		userItemDetailRepository.save(userItemDetail3);
		
		UserItemDetail userItemDetail5 = new UserItemDetail();
		userItemDetail5.setUser(userTest1);
		userItemDetail5.setItem(item3);
		userItemDetail5.setPublish(true);
		userItemDetailRepository.save(userItemDetail5);
		
		UserItemDetail userItemDetail2 = new UserItemDetail();
		userItemDetail2.setUser(userTest1);
		userItemDetail2.setItem(item1);
		userItemDetail2.setFollow(true);
		userItemDetailRepository.save(userItemDetail2);
		
		UserItemDetail userItemDetail4 = new UserItemDetail();
		userItemDetail4.setUser(userTest1);
		userItemDetail4.setItem(item2);
		userItemDetail4.setFollow(true);
		userItemDetailRepository.save(userItemDetail4);
		
		UserItemDetail userItemDetail6 = new UserItemDetail();
		userItemDetail6.setUser(userAdmin);
		userItemDetail6.setItem(item2);
		userItemDetail6.setFollow(true);
		userItemDetailRepository.save(userItemDetail6);
	
		UserItemDetail userItemDetail7 = new UserItemDetail();
		userItemDetail7.setUser(userTest1);
		userItemDetail7.setItem(item3);
		userItemDetail7.setFollow(true);
		userItemDetailRepository.save(userItemDetail7);		
		
		TradePool tradePool1 = new TradePool();
		tradePool1.setUser(userAdmin);
		tradePool1.setAmount(100);
		tradePool1.setItem(item1);
		tradePool1.setDate(new Date());
		tradePoolRepository.save(tradePool1);
		
		TradePool tradePool2 = new TradePool();
		tradePool2.setUser(userTest1);
		tradePool2.setAmount(150);
		tradePool2.setItem(item1);
		tradePool2.setDate(new Date());
		tradePoolRepository.save(tradePool2);
		
		TradePool tradePool3 = new TradePool();
		tradePool3.setUser(userTest1);
		tradePool3.setAmount(200);
		tradePool3.setItem(item2);
		tradePool3.setDate(new Date());
		tradePoolRepository.save(tradePool3);

		//Date dd = new Date();
		
		
		TradePool tradePool4 = new TradePool();
		tradePool4.setUser(userAdmin);
		tradePool4.setAmount(300);
		tradePool4.setItem(item3);
		tradePool4.setDate(new Date());
		
		tradePoolRepository.save(tradePool4);		
		
		TradePool tradePool5 = new TradePool();
		tradePool5.setUser(userAdmin);
		tradePool5.setAmount(400);
		tradePool5.setItem(item3);
		tradePool5.setDate(new Date());
		tradePool5.setMessageDate(tradePool5.getDate().toString());
		tradePoolRepository.save(tradePool5);		

	}
}
