package auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auction.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
