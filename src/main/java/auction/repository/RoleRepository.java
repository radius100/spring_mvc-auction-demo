package auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
