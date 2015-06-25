package auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Image;
import auction.entity.Item;
import auction.repository.ImageRepository;
import auction.repository.ItemRepository;

@Service
@Transactional
public class ImageService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	public void save(Image image, Item item){
		
		image.setItem(item);
				
		imageRepository.save(image);
	}

	public Image getOne(int id) {
				
		return imageRepository.findOne(id);
	}

}
