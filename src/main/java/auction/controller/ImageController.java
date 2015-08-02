package auction.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import auction.entity.Image;
import auction.service.ImageService;
import auction.utils.DateTimeUtils;
import auction.builder.ItemDetailBuilder;

@Controller
public class ImageController {

	@Autowired
	private ItemDetailBuilder itemDetailBuilder;

	@Autowired
	private ImageService imageService;

	static final Logger logger = Logger.getLogger(ImageController.class);


	@RequestMapping("/images/image-{id}.jpeg")
	@ResponseBody public ResponseEntity<?> processJPEG(@PathVariable int id) throws IOException {
			
		return showImage(imageService.getOne(id));
	}
	
	@RequestMapping("/images/image-{id}.jpg")
	@ResponseBody public ResponseEntity<?> processJPG(@PathVariable int id) throws IOException {
			
		return showImage(imageService.getOne(id));
	}

	@RequestMapping("/images/image-{id}.png")
	@ResponseBody public ResponseEntity<?> processPNG(@PathVariable int id) throws IOException {
			
		return showImage(imageService.getOne(id));
	}

	@RequestMapping("/images/image-{id}.bmp")
	@ResponseBody public ResponseEntity<?> processBMP(@PathVariable int id) throws IOException {
			
		return showImage(imageService.getOne(id));
	}

	@RequestMapping("/images/image-{id}.gif")
	@ResponseBody public ResponseEntity<?> processGIF(@PathVariable int id) throws IOException {
			
		return showImage(imageService.getOne(id));
	}
	
	
	public ResponseEntity<?> showImage(Image image) throws IOException {
			
		if(image != null)
			return ResponseEntity
					.ok()
					.contentType(MediaType.parseMediaType(image.getContentType()))
					.body(new InputStreamResource(new ByteArrayInputStream(image.getBody())));
		
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody public String addImage(@RequestPart("file") MultipartFile file, 
			@RequestParam(value="id") int id) throws IOException {
		
		//добавить обработку аватарки
		//обработка ошибок добавлению/ошибке
		
		if ( !
				("image/png".equals(file.getContentType()) ||  
				 "image/bmp".equals(file.getContentType()) ||
				 "image/jpeg".equals(file.getContentType())||
				 "image/gif".equals(file.getContentType())
				) 
				|| file.getSize() > 250000 
			)
			return "fail";
		
		
		Image image = new Image();
			
		logger.info(file.getOriginalFilename());
		logger.info(file.getContentType());
		logger.info(id);
			
		image.setBody(file.getBytes());
		image.setContentType(file.getContentType());
		imageService.save(image,itemDetailBuilder.setOne(id).build());
		
		return "ok";
	}

}
