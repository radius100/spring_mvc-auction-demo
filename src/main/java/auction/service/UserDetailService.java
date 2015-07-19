package auction.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.User;
import auction.entity.UserDetail;
import auction.repository.UserDetailRepository;
import auction.repository.UserRepository;

@Service
@Transactional
public class UserDetailService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;
	
	
	public boolean save(UserDetail userDetail, Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
		
		userDetail.setId(user.getId());
		
		userDetailRepository.save(userDetail);
		
		return true;
	}


	public UserDetail getOne(Principal principal) {
		
		User user = userRepository.findOneByName(principal.getName());
		
		return userDetailRepository.findOne(user.getId());
	}

}
