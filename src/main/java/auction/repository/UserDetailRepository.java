package auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{

}
