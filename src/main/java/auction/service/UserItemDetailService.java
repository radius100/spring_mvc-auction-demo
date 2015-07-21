package auction.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.builder.UserItemDetailBuilder;


@Transactional
@Service
public class UserItemDetailService {

	@Autowired
	UserItemDetailBuilder userItemDetailBuilder;

	
	public String toggleHide(Principal principal, int id) {
		
		if(principal == null)
			return "fail";

	 	boolean hideBool =
	 			
	 			userItemDetailBuilder
	 				.setUser(principal)
		   			.setItem(id)
		   			.setFindByUserAndItemAndHideTrue()
		   			.toggleHide()
		   			.getHide();

	 	
		if( hideBool == true )
			return "Hidden";
		else
			return "Show";

	}

	public String toggleCollapse(Principal principal, int id) {
		
		if( principal == null )
			return "fail";
		
	 	boolean collapseBool = 

	 			userItemDetailBuilder
		    		.setUser(principal)
		    		.setItem(id)
		    		.setFindByUserAndItemAndCollapseTrue()
		    		.toggleCollapse()
		    		.getCollapse();
	
	 	
	 	if( collapseBool )
	  		return "Collapsed";
	  	else
	  		return "Expanded";
	 
	}
	
	public String toggleFollow(Principal principal, int id) {
	
		if( principal == null )
			return "fail";
	
		boolean followBool = 
				
 			userItemDetailBuilder
	    		.setUser(principal)
	    		.setItem(id)
	    		.setFindByUserAndItemAndFollowTrue()
	    		.toggleFollow()
	    		.getFollow();

 	
		if( followBool )
			return "follow";
		else
			return "unfollow";

	}
}
