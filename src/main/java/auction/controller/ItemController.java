package auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.entity.Item;
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

	@RequestMapping("/items/item-{id}")
	public String detail(Model model, @PathVariable int id) {

		Item item = itemService.findOne(id);

		model.addAttribute("item", item);
		model.addAttribute("publish", itemUserDetailService.findItemUserPublish(item));
		//publish-item-trade-detail
		model.addAttribute("p", tradePoolService.findItemTradeDetail(item));		
		model.addAttribute("follows", itemUserDetailService.findItemUsersFollow(item));
//		int i=1;
//		i=i/(i-1);
		return "item-user-detail";
		//return "index";
	}
}
