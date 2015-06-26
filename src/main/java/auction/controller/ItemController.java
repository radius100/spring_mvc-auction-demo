package auction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import auction.entity.Item;
import auction.service.ItemService;
import auction.service.ItemUserDetailService;
import auction.service.TradePoolService;
import auction.service.UserService;
import auction.utils.DateTimeUtils;

@Controller
public class ItemController {

	@ModelAttribute("item")
	public Item constract(){
		return new Item();
	}
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private TradePoolService tradePoolService;
	
	@Autowired
	private ItemUserDetailService itemUserDetailService;

	
	@RequestMapping("/items")
	public String users(Model model) {

		//model.addAttribute("items", itemService.findAll());

		return "items";
	}

	@RequestMapping("/items/item-{id}")
	public String showItem(Principal principal, Model model, @PathVariable int id) {
		
		model.addAttribute("item", itemService.getOne((null != principal)?principal.getName():"",id));
		model.addAttribute("itemJson", itemService.getTradePoolByItemJson(id));
		
		return "item";
	}

	@RequestMapping("/items/item-{id}/tradepool.json")
	@ResponseBody
	public ResponseEntity<?> showItemTradePool(@PathVariable int id) {
		
			
		return ResponseEntity.ok()
	            //.contentLength(image.getSize())
	            //.body(new String("[ { 'id': 1, 'name': 'Item 3'}, { 'id': 11, 'name': 'Item 33' } ]"));
				.body(itemService.getTradePoolByItemJson(id));
	}
	
	
	@RequestMapping("/item/register")
	public String showRegister(){
		
		return "item-register";
	}

	
	@RequestMapping(value="/item/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("item") Item item, Principal principal){
		
		itemService.save(item,userService.getOne(principal.getName()));
		
		return "redirect:/item/register.html?success=true";
	}

	@RequestMapping("/item-{id}/edit")
	public String showEdit(Model model, Principal principal, @PathVariable int id){
		
				
		if ( userService.isOwner(principal.getName(),id) ) {
			
			Item item = itemService.getOne(null, id);
			
			model.addAttribute("item", item);
			model.addAttribute("isEdit", true);
			model.addAttribute("formatPublishDate", DateTimeUtils.getDateAsStringFormat(item.getPublishDate()));
			model.addAttribute("formatStartDate", DateTimeUtils.getDateAsStringFormat(item.getStartDate()));
			model.addAttribute("formatFinishDate", DateTimeUtils.getDateAsStringFormat(item.getFinishDate()));
			
			return "item-edit";
		}
			
		else 
			return "redirect:/items/item-{id}.html";
		
	}

	@RequestMapping(value="/item-{id}/edit",method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("item") Item item, Principal principal, @PathVariable int id){
		
		//itemService.save(item,userService.getOne(principal.getName()));
		
		itemService.update(item,id);
		
		return "redirect:/item-{id}/edit.html?success=true";
	}

	
	
	/*	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user){
		
		userService.save(user);
		return "redirect:/register.html?success=true";
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
