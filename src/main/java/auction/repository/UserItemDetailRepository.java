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

//	public List<User> findByItemAndFollowTrue(Item item);

	//public User findUserByItemAndPublishTrue(Item item);
	
	public UserItemDetail findOneByItemAndPublishTrue(Item item);//findUserByItemAndPublishTrue(Item item);
	
}
