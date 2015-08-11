package auction.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
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


	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";
	}

	
	@InitBinder
	public void initBinderItem(WebDataBinder binder){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
		
		binder.registerCustomEditor(Date.class, "publishDate", new CustomDateEditor(simpleDateFormat, true));
		binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(simpleDateFormat, true));
		binder.registerCustomEditor(Date.class, "finishDate", new CustomDateEditor(simpleDateFormat, true));
	}

	
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

	static final Logger logger = Logger.getLogger(ItemController.class);
	
	
/*	
	@RequestMapping("/admin/items")
	public String items(Model model) {

		return "items";
	}
*/
	/*	
	@RequestMapping("/admin/users")
	public String items(Model model) {

		return "users";
	}
	 */

	
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
	public String showEdit(Model model, Principal principal, Locale locale, @PathVariable int id) {

		if (userService.isOwner(principal, id)) {

			Item item = itemDetailBuilder
					.setOne(id)
					.build();

			model.addAttribute("item", item);
			
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

			return "redirect:/item-{id}/edit.html?success=fail";
		}
			
		
		if (userService.isOwner(principal, id)) {

			itemService.update(item, id, principal, locale);
			return "redirect:/item-{id}/edit.html?success=true";
		}
		
		else
			return "redirect:/items/item-{id}.html";
		
	}

	
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
	
	
	@RequestMapping("/items/item-{id}/datetime")
	@ResponseBody ResponseEntity<?> getDateTimeAdviseAndCheck(
			Principal principal, 
			Locale locale, 
			@PathVariable int id,
			@RequestParam String PublishDateInputBox,
			@RequestParam String StartDateInputBox,
			@RequestParam String FinishDateInputBox) {

  
		return ResponseEntity
				.ok()
				.body( itemService.getDateTimeAdviseAndCheck(principal, id, locale,
						PublishDateInputBox, StartDateInputBox, FinishDateInputBox ));
	}

}