package auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;

public interface UserItemDetailRepository extends JpaRepository<UserItemDetail, Integer>{

	public UserItemDetail findByItemAndPublishTrue(Item item);
	
	public List<UserItemDetail> findByItemAndFollowTrue(Item item);

	public int countUserDistinctByItemAndFollowTrue(Item item);

	public UserItemDetail findOneByFollowTrueAndUserAndItem(User user, Item item);

	public List<UserItemDetail> findByItem(Item item);

	public UserItemDetail findOneByUserAndItemAndPublishTrue(User user, Item item);

	public UserItemDetail findOneByPublishTrueAndUserAndItem(User user, Item item);

	public UserItemDetail findOneByBuyTrueAndUserAndItem(User user, Item item);

	//не работает
	public List<User> findUserListByItemAndFollowTrue(Item item);
	
	public List<UserItemDetail> findItemsByUserAndPublishTrue(User user);
	
	public List<UserItemDetail> findItemsByUserAndFollowTrue(User user);
	
	public UserItemDetail findOneByItemAndPublishTrue(Item item);

	public UserItemDetail findByItemAndUserAndFollowTrue(Item item, User user);
	
}
