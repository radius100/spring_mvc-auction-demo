package auction.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
	     
        return "error";
    }

/*	
	@InitBinder
	public void initBinderItem(WebDataBinder binder){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
		
		binder.registerCustomEditor(Date.class, "publishDate", new CustomDateEditor(simpleDateFormat, false));
		binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(simpleDateFormat, false));
		binder.registerCustomEditor(Date.class, "finishDate", new CustomDateEditor(simpleDateFormat, false));
	}

/*	
	@ModelAttribute("item11")
	public Item constract() {
		return new Item();
	}
*/
}
