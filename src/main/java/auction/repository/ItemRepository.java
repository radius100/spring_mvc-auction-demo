package auction.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	public List<Item> findByActiveTrueAndBlockFalse(Pageable pageable);
	
	public List<Item> findByArchiveTrueAndBlockFalse(PageRequest pageRequest);
	
	public List<Item> findByPreTradingTrueAndBlockFalse(PageRequest pageRequest);
	
	public List<Item> findByTradingTrueAndBlockFalse(PageRequest pageRequest);
	
	public int countByActiveTrueAndBlockFalse();
	
	public int countByArchiveTrueAndBlockFalse();

	public int countByTradingTrueAndBlockFalse();
	
	public int countByPreTradingTrueAndBlockFalse();
	
	public List<Item> findByArchiveFalse();




}
