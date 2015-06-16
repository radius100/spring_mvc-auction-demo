package auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	public List<Item> findItemByActiveTrueAndSellFalseAndBlockFalse();

}
