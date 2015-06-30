package auction.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.service.ItemAllDetailBuilder;


@Controller
public class IndexController {

	@Autowired
	private ItemAllDetailBuilder itemAllDetailBuilder;
	

	@RequestMapping("/index")
	public String index(Model model, Principal principal) {

		model.addAttribute("items", itemAllDetailBuilder.getAll(principal).buildAll());
		return "index";
	}

}
