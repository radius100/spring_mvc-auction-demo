
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
import auction.utils.DateTimeUtils;

@Service
@Transactional
public class ItemDetailBuilder {

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

	public Item build() {

		return item;
	}

	public ItemDetailBuilder getOne(int id) {

		item = itemRepository.findOne(id);
		return this;
	}

	public ItemDetailBuilder getOne(Item item) {

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

	
	public ItemDetailBuilder getFollowersCount() {

		item.setFollowersCount(userItemDetailRepository.countUserDistinctByItemAndFollowTrue(item));
		return this;
	}

	public ItemDetailBuilder getTradersCount() {

		item.setTradersCount(tradePoolRepository.countDistinctUserByItem(item));
		return this;
	}

	public ItemDetailBuilder getCurrentAmount() {

		TradePool tradePool = tradePoolRepository.findFirstByItemOrderByAmountDesc(item);

		if (tradePool != null)
			item.setCurrentAmount(tradePool.getAmount());
		else
			item.setCurrentAmount(-1);

		return this;
	}

	public ItemDetailBuilder getIsFollowByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByFollowTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setFollowedByCurrentUser(true);
			else
				item.setFollowedByCurrentUser(false);
		}
		return this;
	}

	public ItemDetailBuilder getIsBuyByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByBuyTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setBuyByCurrentUser(true);
			else
				item.setBuyByCurrentUser(false);

		}
		return this;
	}

	public ItemDetailBuilder getIsTradeByPrincipal() {

		if (tradePoolRepository.countByUserAndItem(user, item) > 0)
			item.setTradeedByCurrentUser(true);
		else
			item.setTradeedByCurrentUser(false);

		return this;
	}
	
	public ItemDetailBuilder getIsHide() {

		if (user != null) {

			if (userItemDetailRepository.findByItemAndUserAndHideTrue(item,user) != null)
				item.setHide(true);
			else 
				item.setHide(false);
		}
		
		return this;
	}

	public ItemDetailBuilder getIsCollapse() {
		
		if (user != null) {

			if (userItemDetailRepository.findByUserAndItemAndCollapseTrue(user,item) != null)
				item.setCollapse(true);
			else 
				item.setCollapse(false);

		}
	
		return this;
	}

	public ItemDetailBuilder getIsPublishByPrincipal() {

		if (user != null) {

			UserItemDetail userItemDetail = userItemDetailRepository.findOneByPublishTrueAndUserAndItem(user, item);

			if (userItemDetail != null)
				item.setPublishedByCurrentUser(true);
			else
				item.setPublishedByCurrentUser(false);

		}
		
		return this;
	}

	// Почему сразу тип User не возвращает??
	public ItemDetailBuilder getPublisher() {

		UserItemDetail userItemDetail = userItemDetailRepository.findOneByItemAndPublishTrue(item);
		
		item.setPublisher(userItemDetail.getUser());
		return this;
	}
	
	public ItemDetailBuilder getTradePool() {

		List<TradePool> tradePools = tradePoolRepository.findByItem(item);

		for (TradePool tradePool : tradePools) {

			tradePool.setMessageDate(DateTimeUtils.getDateAsString(tradePool.getDate()));
			tradePool.setMessageTime(DateTimeUtils.getTimeAsString(tradePool.getDate()));
		}

		item.setTradePools(tradePools);

		return this;
	}

	public ItemDetailBuilder getTraders() {

		Set<User> setUsers = new HashSet<User>();
		
		List<TradePool> tradePools = tradePoolRepository.findByItem(item);
				
		for (TradePool tradePool : tradePools)
			setUsers.add(tradePool.getUser());

		List<User> listUsers = new ArrayList<User>(setUsers);
		item.setTraders(listUsers);

		return this;
	}

	public ItemDetailBuilder getFollowers() {

		Set<User> setUsers = new HashSet<User>();
		
		List<UserItemDetail> userItemDetails = userItemDetailRepository.findByItemAndFollowTrue(item);
		
		for (UserItemDetail userItemDetail : userItemDetails)
			setUsers.add(userItemDetail.getUser());

		List<User> listUsers = new ArrayList<User>(setUsers);
		item.setFollowers(listUsers);

		return this;
	}

	public ItemDetailBuilder createItemDateMessage4IndexJsp() {

		DateTimeUtils.createItemDateMessage4IndexJsp(item);
		return this;
	}

}
