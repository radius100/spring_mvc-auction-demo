package auction.service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.builder.ItemDetailBuilder;
import auction.json.TradePoolByItemJson;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;
import auction.utils.DateTimeUtils;

@Service
@Transactional
public class ItemService {

	static final Logger logger = Logger.getLogger(ItemService.class);
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemDetailBuilder itemDetailBuilder;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	public void save(Item item, Principal principal) {

		item.setActive(true);
		itemRepository.save(item);

		UserItemDetail userItemDetail = new UserItemDetail();
		userItemDetail.setItem(item);
		userItemDetail.setUser(userRepository.findOneByName(principal.getName()));
		userItemDetail.setPublish(true);
		userItemDetailRepository.save(userItemDetail);
	}

	public void update(Item itemToSave, int itemId, Locale locale) {

		Item item = itemRepository.findOne(itemId);

		item.setId(itemId);
		item.setName(itemToSave.getName());
		item.setDescr(itemToSave.getDescr());
		item.setFullDescr(itemToSave.getFullDescr());
		item.setStartAmount(itemToSave.getStartAmount());

		logger.info(itemToSave.getPublishDateAsString());
		logger.info(itemToSave.getStartDateAsString());
		logger.info(itemToSave.getFinishDateAsString());
		//dd-MMM-YYYY HH:mm
		//24-Jul-2015 16:25
		//Jul-2015 16:16
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-YYYY HH:mm");
		//formatter.withLocale(locale);
		formatter.withLocale(Locale.ENGLISH);
		//DateTime dt = formatter.parseDateTime(item.getStartDateAsString());
		DateTime dt = formatter.parseDateTime("23-Jul-2015 14:19");
		item.setStartDate(dt.toDate());
		
	
	/*	
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-YYYY HH:mm");
		 String startDateAsString = itemToSave.getStartDateAsString();
		 try {
			Date date = formatter.parse(startDateAsString);
		} catch (ParseException e) {
			//int i=1;i=i/(i-1);
			e.printStackTrace();
		}
	*/	
		
	/*	
		DateTime date = DateTime.parse(itemToSave.getStartDateAsString(), 
				DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm"));
		item.setStartDate(date.toDate());
		logger.info(date.toString());
	*/
		
		//DateTime dt = formatter.parseDateTime(itemToSave.getPublishDateAsString());
		//item.setPublishDate(dt.toDate());

		//dt = formatter.parseDateTime(itemToSave.getStartDateAsString());
		//item.setStartDate(dt.toDate());

		//dt = formatter.parseDateTime(itemToSave.getFinishDateAsString());
		//item.setFinishDate(dt.toDate());

		itemRepository.save(item);
	}

	public String getTradePoolByItemJson(Principal principal, int id) {

				
		List<TradePoolByItemJson> tpJs = new ArrayList<TradePoolByItemJson>();
		List<TradePool> tradePools = tradePoolRepository.findByItemOrderByAmountDesc(itemRepository.findOne(id));

		for (TradePool tradePool : tradePools) {

			TradePoolByItemJson tpJ = new TradePoolByItemJson(tradePool.getUser().getName(), tradePool.getAmount(), DateTimeUtils.getDateAsString(tradePool.getDate()),
					DateTimeUtils.getTimeAsString(tradePool.getDate()));

			tpJs.add(tpJ);
		}

		Gson gson = new GsonBuilder()
			.disableHtmlEscaping()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting()
			.serializeNulls()
			.create();
		
		return gson.toJson(tpJs);
	}

	public String getNewItemId(Principal principal) {
		
		Item item = new Item();
		User user = userRepository.findOneByName(principal.getName());
		item.setName("View lot detail");
		
		itemRepository.save(item);
		
		UserItemDetail userItemDetail = new UserItemDetail();
		userItemDetail.setUser(user);
		userItemDetail.setItem(item);
		userItemDetail.setPublish(true);
		userItemDetail.setPreActive(true); 
		
		userItemDetailRepository.save(userItemDetail);
		
		userItemDetail = userItemDetailRepository.findByUserAndItemAndPreActiveTrue(user,item);
		
		item = userItemDetail.getItem();
		
		return Integer.toString(item.getId());
	}

	public String delete(Principal principal, int id) {
		
		/*
		Item item = itemDetailBuilder
						.getOne(id)
						.setPrincipal(principal)
						.checkDeletable()
						.build();
		*/
		
		Item item = itemRepository.findOne(id);
		
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByItem(item);
		for(UserItemDetail userItemDetail : userItemDetails)
			userItemDetailRepository.delete(userItemDetail.getId());
		
		List<TradePool> tradePools = tradePoolRepository.findByItem(item);
		for(TradePool tradePool : tradePools)
			tradePoolRepository.delete(tradePool.getId());

		itemRepository.delete(id);
		return "Delete";
	}

	public String getCountDown(int id, Locale locale) {
		
			return itemDetailBuilder
			 		.setOne(id)
			 		.setLocale(locale)
			 		.setIsPreTrading()
			 		.setIsTrading()
			 		.getCountDownString();
	}

}
