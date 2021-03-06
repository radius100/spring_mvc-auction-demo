package auction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.Role;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserDetail;
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
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setEmail("admin@gmail.com");
		userAdmin.setEnabled(true);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		User userTest = new User();
		userTest.setName("test");
		userTest.setPassword(encoder.encode("12345"));
		userTest.setEmail("test@gmail.com");
		userTest.setEnabled(true);
		roles.clear();
		roles.add(roleUser);
		userTest.setRoles(roles);
		userRepository.save(userTest);

		User userTest1 = new User();
		userTest1.setName("testuser");
		userTest1.setPassword(encoder.encode("12345"));
		userTest1.setEmail("test1@gmail.com");
		userTest1.setEnabled(true);
		roles.clear();
		roles.add(roleUser);
		userTest1.setRoles(roles);
		userRepository.save(userTest1);

		UserDetail userDetail = new UserDetail();
		userDetail.setId(userAdmin.getId());
		userDetail.setFirstName("Admin4ik");
		userDetailRepository.save(userDetail);
		
		UserDetail userDetail1 = new UserDetail();
		userDetail1.setId(userTest1.getId());
		userDetailRepository.save(userDetail1);
		
		UserDetail userDetail2 = new UserDetail();
		userDetail2.setId(userTest.getId());
		userDetailRepository.save(userDetail2);

		
		Item item1 = new Item();
		item1.setName("��� 1");
		item1.setStartAmount(10);
//		item1.setStartDate(new Date());
		item1.setActive(true);
		item1.setBlock(false);
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setName("��� 2");
		item2.setStartAmount(10);
//		item2.setStartDate(new Date());
		item2.setActive(true);
		item2.setBlock(false);
		itemRepository.save(item2);
		
		DateTime now = new DateTime();
		
		Item item3 = new Item();
		item3.setName("������ ��� Meizu");
		item3.setStartAmount(100);
		item3.setDescr("����� ������ ��� �� ��������! Meizu MX5 Pro! ����������");
		item3.setFullDescr("Meizu MX5 � ����������� ������ ����������� ��������� �� ���������� �������������. ���������� �������� �����������, ����������� �����, � ����� ��������� ������������. ������, ��� ��������� � ����� ��� ������� �� Meizu MX4 � ����������� ������� ����� �������, ��������� ���� ������ � 5,36-�������� ������� ������� ����� � ����. ���������� IPS ������� ���������� 1920�1152 �����, ��� ������������ ����������� �������� �����������.");
		item3.setPublishDate(now.plusMinutes(10).toDate());
		item3.setStartDate(now.plusDays(2).toDate());
		item3.setFinishDate(now.plusWeeks(1).toDate());
		//item3.setActive(true);
		item3.setBlock(false);
		itemRepository.save(item3);
		
		Item item4 = new Item();
		item4.setName("��� LG G4");
		item4.setStartAmount(200);
		item4.setDescr("������ ��� ��� � ��������!");
		item4.setFullDescr("LG G4 � ����������� ������ ����������� ���������. ���������� �������� �����������, ����������� �����, � ����� ��������� ������������. ������, ��� ��������� � ����� ��� ������� �� LG G4 � ����������� ������� ����� �������, ��������� ���� ������ ������� ����� � ����. ���������� IPS ������� ���������� 1920�1152 �����, ��� ������������ ����������� �������� �����������.");
//		item4.setPublishDate(new Date());
//		item4.setStartDate(new Date());
//		item4.setFinishDate(new Date());
		item4.setActive(true);
		item4.setBlock(false);
		itemRepository.save(item4);

		
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

		UserItemDetail userItemDetail8 = new UserItemDetail();
		userItemDetail8.setUser(userTest);
		userItemDetail8.setItem(item4);
		userItemDetail8.setPublish(true);
		userItemDetailRepository.save(userItemDetail8);

		
		UserItemDetail userItemDetail2 = new UserItemDetail();
		userItemDetail2.setUser(userTest1);
		userItemDetail2.setItem(item1);
		userItemDetail2.setFollow(true);
		userItemDetailRepository.save(userItemDetail2);
		
		UserItemDetail userItemDetail4 = new UserItemDetail();
		userItemDetail4.setUser(userTest);
		userItemDetail4.setItem(item2);
		userItemDetail4.setFollow(true);
		userItemDetailRepository.save(userItemDetail4);
		
		UserItemDetail userItemDetail6 = new UserItemDetail();
		userItemDetail6.setUser(userAdmin);
		userItemDetail6.setItem(item4);
		userItemDetail6.setFollow(true);
		userItemDetailRepository.save(userItemDetail6);
		

		UserItemDetail userItemDetail7 = new UserItemDetail();
		userItemDetail7.setUser(userAdmin);
		userItemDetail7.setItem(item3);
		userItemDetail7.setFollow(true);
		userItemDetailRepository.save(userItemDetail7);		
		
		UserItemDetail userItemDetail9 = new UserItemDetail();
		userItemDetail9.setUser(userTest1);
		userItemDetail9.setItem(item4);
		userItemDetail9.setFollow(true);
		userItemDetailRepository.save(userItemDetail9);
		
		
		TradePool tradePool1 = new TradePool();
		tradePool1.setUser(userAdmin);
		tradePool1.setAmount(100);
		tradePool1.setItem(item3);
		tradePool1.setDate(new Date());
		tradePoolRepository.save(tradePool1);
		
		TradePool tradePool2 = new TradePool();
		tradePool2.setUser(userAdmin);
		tradePool2.setAmount(150);
		tradePool2.setItem(item4);
		tradePool2.setDate(new Date());
		tradePoolRepository.save(tradePool2);
		
		TradePool tradePool3 = new TradePool();
		tradePool3.setUser(userTest1);
		tradePool3.setAmount(200);
		tradePool3.setItem(item1);
		tradePool3.setDate(new Date());
		tradePoolRepository.save(tradePool3);
		
		TradePool tradePool4 = new TradePool();
		tradePool4.setUser(userTest);
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

		TradePool tradePool6 = new TradePool();
		tradePool6.setUser(userAdmin);
		tradePool6.setAmount(300);
		tradePool6.setItem(item4);
		tradePool6.setDate(new Date());
		tradePoolRepository.save(tradePool6);
		
		TradePool tradePool7 = new TradePool();
		tradePool7.setUser(userAdmin);
		tradePool7.setAmount(400);
		tradePool7.setItem(item4);
		tradePool7.setDate(new Date());
		tradePoolRepository.save(tradePool7);		
		
		TradePool tradePool8 = new TradePool();
		tradePool8.setUser(userAdmin);
		tradePool8.setAmount(500);
		tradePool8.setItem(item3);
		tradePool8.setDate(new Date());
		tradePool8.setMessageDate(tradePool8.getDate().toString());
		tradePoolRepository.save(tradePool8);		

		
	}
}
