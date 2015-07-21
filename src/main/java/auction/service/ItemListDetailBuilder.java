package auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.repository.ItemRepository;


@Service
@Transactional
public class ItemListDetailBuilder {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemDetailBuilder itemDetailBuilder;

	private List<Item> items;


	public List<Item> buildAll() {

		return items;
	}

	public ItemListDetailBuilder getAll() {

			items = itemRepository.findByActiveTrueAndSellFalseAndBlockFalse();
				
			for (Item item : items){
			
				item = itemDetailBuilder
						.getOne(item)
						.getFollowersCount()
						.getTradersCount()
						.getCurrentAmount()
						.getIsFollowByPrincipal()
						.getIsPublishByPrincipal()
						.build();

			}
		
		return this;
	}

}
