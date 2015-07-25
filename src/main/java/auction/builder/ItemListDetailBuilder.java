package auction.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.repository.ItemRepository;
import auction.session.Pagination;

@Service
@Transactional
public class ItemListDetailBuilder {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemDetailBuilder itemDetailBuilder;

	private List<Item> items;


	public List<Item> build() {

		return items;
	}

	public ItemListDetailBuilder getQuery(Pagination pagination) {

			items = itemRepository.findByActiveTrueAndBlockFalse(
						new PageRequest(
								pagination.getPageIndex(), 
								pagination.getItemsPerPage(), 
								pagination.getSortDirection(), 
								"publishDate"));
				
			for (Item item : items){
			
				item = itemDetailBuilder
						.setOne(item)
						.setFollowersCount()
						.setTradersCount()
						.setCurrentAmount()
						.setIsFollowByPrincipal()
						.setIsPublishByPrincipal()
						.build();
			}
		
		return this;
	}

}
