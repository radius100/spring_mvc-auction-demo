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

import auction.entity.UserDetail;
import auction.service.AccountService;
import auction.service.ItemService;
import auction.service.UserDetailService;
import auction.service.UserItemDetailService;


@Controller
public class AccountController {

	@ModelAttribute("userdetail")
	public UserDetail constract() {
		return new UserDetail();
	}
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserItemDetailService userItemDetailService;
	
	
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("userdetail") UserDetail userDetail, Principal principal) {

		if ( (userDetailService.save(userDetail, principal)) == true )
			return "redirect:/account.html?success=true";
		else
			return "redirect:/account.html?success=fail";
		
	}

	
	@RequestMapping(value="/account")
	public String accountProfile(Model model, Principal principal){
		
		model.addAttribute("principal",principal.getName());
		model.addAttribute("userdetail",userDetailService.getOne(principal));
		model.addAttribute("myItems", accountService.getMyItemSettings(principal));
		model.addAttribute("monitoringItems", accountService.getTradingMonitorSettings(principal));

		return "account-profile";
	}
	
	
	@RequestMapping(value="/account/trading-monitor")
	public String trading_monitor(Model model, Principal principal){
		
		model.addAttribute("tables", accountService.getAccountMonitorTables(principal)); 
		
		return "monitor";
	}
	
	
	@RequestMapping(value="/account/my-items")
	public String myItems(Model model, Principal principal){

		model.addAttribute("tables", accountService.getAccountMyItemsTables(principal));
	
		return "my-items";
	}

	
	@RequestMapping("/account-info-my-items")
	@ResponseBody public ResponseEntity<?> showMyItemsTradePool(Principal principal) {

		return ResponseEntity
				.ok()
				.body(accountService.getAccountMyItemsTablesJSON(principal));
	}

	
	@RequestMapping("/account-info-monitor")
	@ResponseBody public ResponseEntity<?> showMonitorTradePool(Principal principal) {

		return ResponseEntity
				.ok()
				.body(accountService.getAccountMonitorTablesJSON(principal));
	}

	
	@RequestMapping("/account/item-{id}/hide")
	@ResponseBody public String doToggleHide(Principal principal, @PathVariable int id) {
	
		return userItemDetailService.toggleHide(principal,id);
	}
	
	
	@RequestMapping("/account/item-{id}/collapse")
	@ResponseBody public String doToggleCollapse(Principal principal, @PathVariable int id) {
	
		return userItemDetailService.toggleCollapse(principal,id);
	}
	
	
	@RequestMapping("/account/new-item")
	@ResponseBody public String addNewItem(Principal principal) {
		
		return itemService.getNewItemId(principal);
	}


	@RequestMapping("/account/item-{id}/delete")
	@ResponseBody public String doDelete(Principal principal, @PathVariable int id) {
	
		return itemService.delete(principal,id);
	}
}