package auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import auction.entity.Role;
import auction.entity.User;
import auction.repository.RoleRepository;
import auction.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	
	public User getOne(int id) {
		return userRepository.findOne(id);
	}

	public User getOne(String name) {
		return userRepository.findOneByName(name);
	}

	public void save(User user) {
		
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

}
