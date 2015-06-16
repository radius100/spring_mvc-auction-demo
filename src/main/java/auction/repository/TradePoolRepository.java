package auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;

public interface TradePoolRepository extends JpaRepository<TradePool, Integer>{

	public List<TradePool> findByItemOrderByAmountDesc(Item item);

	public List<TradePool> findByItem(Item item);

	public int countDistinctUserByItem(Item item);

//	public TradePool findFirstItemByOrderByAmountDesc(Item item);

	public int countByUserAndItem(User user, Item item);

	public TradePool findFirstByItemOrderByAmountDesc(Item item);
	
	//findFirstByOrderByLastnameAsc()
	
	//public TradePool findFirstByOderByAmountDesc(Item item);
}
