package auction.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import auction.entity.Item;
import auction.builder.ItemDetailBuilder;
import auction.service.ItemService;
import auction.service.TradePoolService;
import auction.service.UserItemDetailService;
import auction.service.UserService;


@Controller
public class ItemController {

	@ModelAttribute("item")
	public Item constract() {
		return new Item();
	}

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private TradePoolService tradePoolService;

	@Autowired
	private UserItemDetailService userItemDetailService;

	@Autowired
	ItemDetailBuilder itemDetailBuilder;

	
	@RequestMapping("/items")
	public String users(Model model) {

		return "items";
	}

	
	@RequestMapping("/items/item-{id}")
	public String showItem(Principal principal, Model model, @PathVariable int id) {

		Item item = itemDetailBuilder
				.setOne(id)
				.setPrincipal(principal)
				.setFollowers()
				.setTraders()
				.setIsPublishByPrincipal() 
				.setPublisher()
				.setIsFollowByPrincipal()
				.build();

		model.addAttribute("item", item);

		return "item";
	}

	
	@RequestMapping("/item-{id}/edit")
	public String showEdit(Model model, Principal principal, @PathVariable int id) {

		if (userService.isOwner(principal, id)) {

			Item item = itemDetailBuilder
					.setOne(id)
					.build();

			model.addAttribute("item", item);
			
			Locale locale = LocaleContextHolder.getLocale();
			
			model.addAttribute("formatPublishDate", item.getPublishDateToLocaleString(locale));
			model.addAttribute("formatStartDate", item.getStartDateToLocaleString(locale));
			model.addAttribute("formatFinishDate", item.getFinishDateToLocaleString(locale));

			return "item-edit";
		}

		else
			return "redirect:/items/item-{id}.html";

	}

	
	@RequestMapping(value = "/item-{id}/edit", method = RequestMethod.POST)
	public String doEdit(@ModelAttribute("item") Item item, 
				 BindingResult result, 
				 Principal principal,
				 Locale locale,
				 @PathVariable int id) {

		if(result.hasErrors()){
			//item.setStartDate(null);
			//result.
			return "redirect:/item-{id}/edit.html?success=fail";
		}
			
		
		if (userService.isOwner(principal, id)) {

			itemService.update(item, id, locale);
			return "redirect:/item-{id}/edit.html?success=true";
		}
		
		else
			return "redirect:/items/item-{id}.html";
		
	}

/*	
	@RequestMapping("/item/register")
	public String showRegister() {

		return "item-register";
	}

	
	@RequestMapping(value = "/item/register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("item") Item item, Principal principal) {

		itemService.save(item, principal);
		return "redirect:/item/register.html?success=true";
	}
*/
	
	@RequestMapping("/items/item-{id}/follow")
	@ResponseBody public String toggleFollow(Principal principal, @PathVariable int id) {

		return userItemDetailService.toggleFollow(principal, id);
	}

	
	@RequestMapping("/items/item-{id}/rate-adv")
	@ResponseBody public String getRateAdvs(Principal principal, @PathVariable int id) {

		return tradePoolService.getRateAdvs(principal, id);
	}

	@RequestMapping("/items/item-{id}/countdown")
	@ResponseBody public String getCountDown(Locale locale, @PathVariable int id) {

		return itemService.getCountDown(id, locale);
	}
	
	@RequestMapping(value="/items/item-{id}/rate", method=RequestMethod.POST)
	@ResponseBody public String doRate(Principal principal, @PathVariable int id, @RequestParam String amount) {
	
		return tradePoolService.save(principal,id,amount);
	}

	
	@RequestMapping("/items/item-{id}/tradepool")
	@ResponseBody public ResponseEntity<?> showItemTradePool(Principal principal, @PathVariable int id) {
		
		return ResponseEntity
				.ok()
				.body(itemService.getTradePoolByItemJson(principal, id));
	}

}