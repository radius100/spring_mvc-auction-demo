package auction.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.service.ItemService;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/index")
	public String index(Model model, Principal principal) {

	//	String name = principal.getName();
		String name = "admin";
		
		model.addAttribute("items", itemService.findAll(name));

		return "index";
	}

}
