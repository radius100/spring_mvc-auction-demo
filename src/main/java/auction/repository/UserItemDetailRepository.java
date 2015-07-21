package auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;

public interface UserItemDetailRepository extends JpaRepository<UserItemDetail, Integer>{

	
	public UserItemDetail findByItemAndPublishTrue(Item item);

	public UserItemDetail findByUserAndItemAndHideTrue(User user, Item item);

	public UserItemDetail findByUserAndItemAndFollowTrue(User user, Item item);

	public UserItemDetail findByUserAndItemAndPublishTrue(User user, Item item);
	
	public UserItemDetail findByUserAndItemAndBuyTrue(User user, Item item);
	
	public UserItemDetail findByUserAndItemAndPreActiveTrue(User user, Item item);

	public UserItemDetail findByUserAndItemAndCollapseTrue(User user, Item item);


	public int countUserDistinctByItemAndFollowTrue(Item item);

	
	public List<UserItemDetail> findByItemAndFollowTrue(Item item);

	public List<UserItemDetail> findByItem(Item item);

	
	public List<UserItemDetail> findByUserAndFollowTrue(User user);

	public List<UserItemDetail> findByUserAndHideTrue(User user);

	public List<UserItemDetail> findByUserAndCollapseTrue(User user);

	public List<UserItemDetail> findByUserAndPublishTrue(User user);

}
