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
import auction.service.ItemDetailBuilder;
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

	@Autowired
	ItemDetailBuilder itemDetailBuilder;
	
	@RequestMapping("/items")
	public String users(Model model) {

		//model.addAttribute("items", itemService.findAll());

		return "items";
	}

	@RequestMapping("/items/item-{id}")
	public String showItem(Principal principal, Model model, @PathVariable int id) {
		
		Item item = itemDetailBuilder
				.getOne(id)
				.setPrincipal(principal)
				.getFollowers()
				.getTraders()
				.getPublisher()
				.build();

		model.addAttribute("item", item);
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
			
			//Item item = itemService.getOne(null, id);
			//ItemDetailBuilder itemDetailBuilder;
			Item item = itemDetailBuilder.getOne(id).build();
			
			model.addAttribute("item", item);
			//нужен ли isEdit??
			model.addAttribute("isEdit", true);
			model.addAttribute("formatPublishDate", DateTimeUtils.getDateTimeAsString(item.getPublishDate()));
			model.addAttribute("formatStartDate", DateTimeUtils.getDateTimeAsString(item.getStartDate()));
			model.addAttribute("formatFinishDate", DateTimeUtils.getDateTimeAsString(item.getFinishDate()));
			
			return "item-edit";
		}
			
		else 
			return "redirect:/items/item-{id}.html";
		
	}

	@RequestMapping(value="/item-{id}/edit",method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("item") Item item, Principal principal, @PathVariable int id){
		
		itemService.update(item,id);
		
		return "redirect:/item-{id}/edit.html?success=true";
	}

}
