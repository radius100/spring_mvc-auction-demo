package auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findOneByName(String name);
	
}
