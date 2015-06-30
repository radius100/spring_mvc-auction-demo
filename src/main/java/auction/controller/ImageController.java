package auction.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import auction.entity.Image;
import auction.service.ImageService;
import auction.service.ItemDetailBuilder;

@Controller
public class ImageController {

	@Autowired
	private ItemDetailBuilder itemDetailBuilder;

	@Autowired
	private ImageService imageService;


	@RequestMapping("/images/{id}.jpg")
	@ResponseBody 
	public ResponseEntity<?> showImage(@PathVariable int id) throws IOException {
			
		//int a =1; a=a/(a-1);
		Image image = imageService.getOne(id);
		
		if(image != null)
			return ResponseEntity.ok()
	            //.contentLength(image.getSize())
	            .contentType(MediaType.parseMediaType(image.getContentType()))
	            .body(new InputStreamResource(new ByteArrayInputStream(image.getBody())));
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			
	}

	@RequestMapping(value = "/images/item-{id}/add", method = RequestMethod.POST)
	public void doAddImage(@RequestParam(value="file") MultipartFile file, @PathVariable int id) throws IOException {
		
		//добавить обработку аватарки
		//добавить поддержку *.png, *.bmp etc
		//добавить ограничение на размер файла
		//добавить redirect по удачному добавлению/ошибке
		
		//int a =1; a=a/(a-1);
		Image image = new Image();
				
		if ( "application/jpeg".equals(file.getContentType()) /*|| "application/bmp".equals*/){
			
			image.setContentType(file.getContentType());
			image.setBody(file.getBytes());
			//image.setSize(file.getSize());
			image.setName(file.getName());
			imageService.save(image,itemDetailBuilder.getOne(id).build());
		}

	}

}
