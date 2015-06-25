package auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Image;
import auction.repository.ImageRepository;
import auction.repository.ItemRepository;

@Service
@Transactional
public class ImageService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	public void save(Image image/*, Item item*/){
		
		image.setItem(itemRepository.getOne(1));
		image.setName("1.jpg");
		
		imageRepository.save(image);
	}

	public Image getOne(int id) {
				
		return imageRepository.findOne(id);
	}

}
