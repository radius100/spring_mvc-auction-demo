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

		for (Item item : items)
			item = itemDetailBuilder
				.setOne(item)
				.setFollowersCount()
				.setTradersCount()
				.setCurrentAmount()
				.setIsFollowByPrincipal()
				.setIsPublishByPrincipal()
				.build();
		
		return items;
	}

	public ItemListDetailBuilder getActiveItemList(Pagination pagination) {

			pagination.setGrid(
						itemRepository.countByActiveTrueAndBlockFalse()
					);
		
			try {
				
				items = itemRepository.findByActiveTrueAndBlockFalse(
						new PageRequest(
								pagination.getPageIndex()-1, 
								pagination.getItemsPerPage(), 
								pagination.getSortDirection(), 
								"publishDate")
						);
		
			} catch (Exception e) {
				
				items.clear();
				
			}
			
			return this;
	}
	
	public ItemListDetailBuilder getPreTradingItemList(Pagination pagination) {

			pagination.setGrid(
						itemRepository.countByPreTradingTrueAndBlockFalse()
					);
		
			
			try {
				
				items = itemRepository.findByPreTradingTrueAndBlockFalse(
					new PageRequest(
							pagination.getPageIndex()-1, 
							pagination.getItemsPerPage(), 
							pagination.getSortDirection(), 
							"publishDate")
					);

			} catch (Exception e) {

				items.clear();
				
			}

			return this;
	}

	public ItemListDetailBuilder getTradingItemList(Pagination pagination) {

			pagination.setGrid(
						itemRepository.countByTradingTrueAndBlockFalse()
					);
		
			try {
				
				items = itemRepository.findByTradingTrueAndBlockFalse(
					new PageRequest(
							pagination.getPageIndex()-1, 
							pagination.getItemsPerPage(), 
							pagination.getSortDirection(), 
							"publishDate")
					);
	
			} catch (Exception e) {
				
				items.clear();
	
			}

			return this;
	}

	public ItemListDetailBuilder getArchiveItemList(Pagination pagination) {

			pagination.setGrid(
						itemRepository.countByArchiveTrueAndBlockFalse()
					);
		
			try {
				
				items = itemRepository.findByArchiveTrueAndBlockFalse(
					new PageRequest(
							pagination.getPageIndex()-1, 
							pagination.getItemsPerPage(), 
							pagination.getSortDirection(), 
							"publishDate")
					);
	
			} catch (Exception e) {
				
				items.clear();
				
			}
		
			return this;
	}

}
