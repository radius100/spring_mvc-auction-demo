package auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.service.ItemService;
import auction.service.ItemUserDetailService;
import auction.service.TradePoolService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private TradePoolService tradePoolService;
	
	@Autowired
	private ItemUserDetailService itemUserDetailService;

	
	@RequestMapping("/items")
	public String users(Model model) {

		model.addAttribute("items", itemService.findAll());

		return "items";
	}

	@RequestMapping("/item")
	public String showItemDetail1() {
		
		return "item";
	}

	
	@RequestMapping("/items/item-{id}")
	public String showItemDetail(Model model, @PathVariable int id) {
		
		return "item";
	}
	/*
	@RequestMapping("/items/item-{id}")
	public String detail(Model model, @PathVariable int id) {

		Item item = itemService.findOne(id);

		model.addAttribute("item", item);
		model.addAttribute("publish", itemUserDetailService.findItemUserPublish(item));
		//publish-item-trade-detail
		model.addAttribute("p", tradePoolService.findItemTradeDetail(item));		
		model.addAttribute("follows", itemUserDetailService.findItemUsersFollow(item));

		return "item-user-detail";
	}
	*/
}
