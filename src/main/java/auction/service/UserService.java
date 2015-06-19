package auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auction.entity.User;
import auction.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getOne(int id) {
		return userRepository.findOne(id);
	}

	public User getOne(String name) {
		return userRepository.findOneByName(name);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

}
