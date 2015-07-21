package auction.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.service.ItemListDetailBuilder;


@Controller
public class IndexController {

	@Autowired
	private ItemListDetailBuilder itemListDetailBuilder;
	

	@RequestMapping("/index")
	public String index(Model model) {

		model.addAttribute("items", itemListDetailBuilder.getAll().buildAll());
		return "index";
	}

}
