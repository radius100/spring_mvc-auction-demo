/*
 * public TradePoolBuilder 
 *			
 *			setUser( Principal principal )
 * 			setUser( User user )
 *			setItem( Item item )
 *			setItem( int id )
 *			setFindByUser()
 *	
 *	public List<Item> 
 *			
 *			getItemList()	
 *
 */

package auction.builder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class TradePoolBuilder {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	TradePoolRepository tradePoolRepository;


	TradePool tradePool;
	List<TradePool> tradePools;
	
	List<Item> items; 
	Item item;
	
	User user;
	
	
	public TradePoolBuilder setUser( Principal principal ){
		
		this.user = userRepository.findOneByName(principal.getName());
		return this;
	}
	
	public TradePoolBuilder setUser( User user ){
		
		this.user = user;
		return this;
	}
	
	public TradePoolBuilder setItem( Item item ){
		
		this.item = item;
		return this;
	}
	
	public TradePoolBuilder setItem( int id ){
		
		this.item = itemRepository.findOne(id);
		return this;
	}
	
	public TradePoolBuilder setFindByUser(){
		
		tradePools = tradePoolRepository.findByUser(user);
		return this;
	}
	
	
	public List<Item> getItemList(){

		items = new ArrayList<Item>();
		
		
		
		for( TradePool i : tradePools )
			if( items.contains(i.getItem()) == false )
				items.add(i.getItem());
		
		return items;
	}
	
}
	
