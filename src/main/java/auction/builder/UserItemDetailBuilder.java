/*
 * public UserItemDetailBuilder 
 *			initList( List<UserItemDetail> userItemDetails )
 *			initOne( UserItemDetail userItemDetail )
 *			setUser( Principal principal )
 *			setUser( User user )
 *			setItem( Item item )
 *			setItem( int id )
 *			toggleCollapse()
 *			toggleHide()
 *			toggleFollow()
 *			setFindByUserAndItemAndFollowTrue()
 *			setFindByUserAndFollowTrue()
 *			setFindByUserAndPublishTrue()
 *			setFindByUserAndItemAndCollapseTrue()
 *			setFindByUserAndItemAndHideTrue()
 * public List<Item> 
 *			getItemList()
 * public boolean 	
 *			getCollapse()
 *			getHide()
 *			getFollow()
 */


package auction.builder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.User;
import auction.entity.UserItemDetail;
import auction.repository.ItemRepository;
import auction.repository.UserItemDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class UserItemDetailBuilder {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserItemDetailRepository userItemDetailRepository;


	UserItemDetail userItemDetail;
	List<UserItemDetail> userItemDetails;
	
	List<Item> items; 
	Item item;
	
	User user;
	
	boolean follow;
	boolean collapse;
	boolean hide;
	
	public UserItemDetailBuilder initList( List<UserItemDetail> userItemDetails ){
		
		this.userItemDetails = userItemDetails;
		return this;
	}
	
	public UserItemDetailBuilder initOne( UserItemDetail userItemDetail ){
		
		this.userItemDetail = userItemDetail;
		return this;
	}
	
	
	public UserItemDetailBuilder setUser( Principal principal ){
		
		this.user = userRepository.findOneByName(principal.getName());
		return this;
	}
	
	public UserItemDetailBuilder setUser( User user ){
		
		this.user = user;
		return this;
	}
	
	public UserItemDetailBuilder setItem( Item item ){
		
		this.item = item;
		return this;
	}
	
	public UserItemDetailBuilder setItem( int id ){
		
		this.item = itemRepository.findOne(id);
		return this;
	}
	
	public UserItemDetailBuilder toggleCollapse(){
		
		if( userItemDetail == null ){
			
			UserItemDetail uIDetail = new UserItemDetail();
			uIDetail.setItem(item);
			uIDetail.setUser(user);
			uIDetail.setCollapse(true);
			userItemDetailRepository.save(uIDetail);
			
			collapse = true;
		}
		else {

			userItemDetailRepository.delete(userItemDetail);
			collapse = false;
		}
		
		return this;
	}
	
	public UserItemDetailBuilder toggleHide(){
		
		if( userItemDetail == null){
			
			UserItemDetail uIDetail = new UserItemDetail();
			uIDetail.setItem(item);
			uIDetail.setUser(user);
			uIDetail.setHide(true);
			userItemDetailRepository.save(uIDetail);
			
			hide=true;
			
		}
		else {
			
			userItemDetailRepository.delete(userItemDetail);
			hide=false;
		}		

		return this;
	}
	
	public UserItemDetailBuilder toggleFollow(){
		
		if(userItemDetail == null){
			
			UserItemDetail uIDetail = new UserItemDetail();
			uIDetail.setUser(user);
			uIDetail.setItem(item);
			uIDetail.setFollow(true);
			userItemDetailRepository.save(uIDetail);

			follow = true;
		}
		else{
			
			userItemDetailRepository.delete(userItemDetail);
			
			follow = false;
		}

		
		return this;
	}
	
	public UserItemDetailBuilder setFindByUserAndItemAndFollowTrue(){
		
		userItemDetail = userItemDetailRepository.findByUserAndItemAndFollowTrue(user, item);
		return this;
	}
									
	public UserItemDetailBuilder setFindByUserAndFollowTrue(){
		
		userItemDetails = userItemDetailRepository.findByUserAndFollowTrue(user);		
		return this;
	}
	
	public UserItemDetailBuilder setFindByUserAndPublishTrue(){
		
		userItemDetails = userItemDetailRepository.findByUserAndPublishTrue(user);		
		return this;
	}
	
	public UserItemDetailBuilder setFindByUserAndItemAndCollapseTrue(){
		
		userItemDetail = userItemDetailRepository.findByUserAndItemAndCollapseTrue(user, item);		
		return this;
	}
	
	public UserItemDetailBuilder setFindByUserAndItemAndHideTrue(){
		
		userItemDetail = userItemDetailRepository.findByUserAndItemAndHideTrue(user,item);
		return this;
	}

	
	public List<Item> getItemList(){

		items = new ArrayList<Item>();
		
		for( UserItemDetail i : userItemDetails )
			if( items.contains(i.getItem()) == false )
				items.add(i.getItem());
		
		return items;
	}
	
	public boolean getCollapse(){
		
		return collapse;
	}
	
	public boolean getHide(){
		
		return hide;
	}
	
	public boolean getFollow(){
		
		return follow;
	}

}
	
