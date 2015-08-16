
/* Avaible builder methods:
 	 * 
	 * getOne(int id)
	 * getOne(Item item)
	 * setPrincipal(Principal principal)
	 * setPrincipal(User user)
	 * getFollowersCount()
	 * getTradersCount()
	 * getCurrentAmount()
	 * getIsFollowByPrincipal()
	 * getIsPublishByPrincipal()
	 * getIsBuyByPrincipal()
	 * getIsTradeByPrincipal()
	 * getIsHide()
	 * getIsCollapse
	 * getTradePool()
	 * getTraders()
	 * getFollowers()
	 * getPublisher()
	 * createItemDateMessage4IndexJsp()
	 * 
	 * build()
*/

package auction.builder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;
import auction.utils.DateTimeUtils;

@Service
@Transactional
public class ItemDetailBuilder {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;

	private Item item;
	private User user;
	private Locale locale;

	public Item build() {

		return item;
	}
	
	@RenderMapping(params = "render=details")
	public String getCountDownString() {

		return DateTimeUtils.getCountDownString(
					item,
					messageSource.getMessage("countdown.day", null, locale),
					messageSource.getMessage("countdown.days", null, locale)
				);
	}


	public ItemDetailBuilder setOne(int id) {

		item = itemRepository.findOne(id);
		return this;
	}

	public ItemDetailBuilder setOne(Item item) {

		this.item = item;
		return this;
	}

	public ItemDetailBuilder setPrincipal(Principal principal) {

		if (principal != null)
			user = userRepository.findOneByName(principal.getName());

		return this;
	}

	public ItemDetailBuilder setPrincipal(User user) {

		this.user = user;
		return this;
	}
	
	public ItemDetailBuilder setLocale(Locale locale) {

		this.locale = locale;
		return this;
	}
	
	public ItemDetailBuilder setIsPreTrading() {

		Date date = new Date();
		
		if( date.before(item.getStartDate()) )
			item.setPreTrading(true);

		return this;
	}

	public ItemDetailBuilder setIsTrading() {

		Date date = new Date();
		
		if( date.after(item.getStartDate()) 
				&& date.before(item.getFinishDate()) )
			item.setTrading(true);
		
		return this;
	}
	
	public ItemDetailBuilder setFollowersCount() {

		item.setFollowersCount(userItemDetailRepository.countUserDistinctByItemAndFollowTrue(item));
		return this;
	}

	public ItemDetailBuilder setTradersCount() {

		item.setTradersCount(tradePoolRepository.countDistinctUserByItem(item));
		return this;
	}

	public ItemDetailBuilder setCurrentAmount() {

		TradePool tradePool = tradePoolRepository.findFirstByItemOrderByAmountDesc(item);

		if (tradePool != null)
			item.setCurrentAmount(tradePool.getAmount());
		else
			item.setCurrentAmount(-1);

		return this;
	}

	public ItemDetailBuilder setIsFollowByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findByUserAndItemAndFollowTrue(user, item);

			if (userItemDetail != null)
				item.setFollowedByCurrentUser(true);
			else
				item.setFollowedByCurrentUser(false);
		}
		return this;
	}

	public ItemDetailBuilder setIsBuyByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findByUserAndItemAndBuyTrue(user, item);

			if (userItemDetail != null)
				item.setBuyByCurrentUser(true);
			else
				item.setBuyByCurrentUser(false);

		}
		return this;
	}

	public ItemDetailBuilder setIsTradeByPrincipal() {

		if (tradePoolRepository.countByUserAndItem(user, item) > 0)
			item.setTradeedByCurrentUser(true);
		else
			item.setTradeedByCurrentUser(false);

		return this;
	}
	
	public ItemDetailBuilder setIsHide() {

		if (user != null) {

			if (userItemDetailRepository.findByUserAndItemAndHideTrue(user, item) != null)
				item.setHide(true);
			else 
				item.setHide(false);
		}
		
		return this;
	}

	public ItemDetailBuilder setIsCollapse() {
		
		if (user != null) {

			if (userItemDetailRepository.findByUserAndItemAndCollapseTrue(user,item) != null)
				item.setCollapse(true);
			else 
				item.setCollapse(false);

		}
	
		return this;
	}

	public ItemDetailBuilder setIsPublishByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findByUserAndItemAndPublishTrue(user, item);

			if (userItemDetail != null)
				item.setPublishedByCurrentUser(true);
			else
				item.setPublishedByCurrentUser(false);

		}
		
		return this;
	}

	public ItemDetailBuilder setPublisher() {

		UserItemDetail userItemDetail = userItemDetailRepository.findByItemAndPublishTrue(item);
		
		item.setPublisher(userItemDetail.getUser());
		return this;
	}
	
	public ItemDetailBuilder setTradePool() {

		List<TradePool> tradePools = tradePoolRepository.findByItem(item);

		for (TradePool tradePool : tradePools) {

			tradePool.setMessageDate(DateTimeUtils.getDateAsString(tradePool.getDate()));
			tradePool.setMessageTime(DateTimeUtils.getTimeAsString(tradePool.getDate()));
		}

		item.setTradePools(tradePools);

		return this;
	}

	public ItemDetailBuilder setTraders() {

		Set<User> setUsers = new HashSet<User>();
		
		List<TradePool> tradePools = tradePoolRepository.findByItem(item);
				
		for (TradePool tradePool : tradePools)
			setUsers.add(tradePool.getUser());

		List<User> listUsers = new ArrayList<User>(setUsers);
		item.setTraders(listUsers);

		return this;
	}

	public ItemDetailBuilder setFollowers() {

		Set<User> setUsers = new HashSet<User>();
		
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByItemAndFollowTrue(item);
		
		for (UserItemDetail userItemDetail : userItemDetails)
			setUsers.add(userItemDetail.getUser());

		List<User> listUsers = new ArrayList<User>(setUsers);
		
		item.setFollowers(listUsers);
		
		return this;
	}

	public ItemDetailBuilder checkDeletable() {

		Date startDate = item.getStartDate();
		
		if( startDate != null ){
			if( item.getStartDate().before(new Date()) )
				item.setOwnerDeletable(false);
			else
				item.setOwnerDeletable(true);
		}
		else
			item.setOwnerDeletable(true);
		
		return this;
	}
/*	
	public ItemDetailBuilder createItemDateMessage4IndexJsp() {

//		DateTimeUtils.createItemDateMessage4IndexJsp(item);
		return this;
	}
*/	
}
