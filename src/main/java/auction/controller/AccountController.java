package auction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auction.service.AccountService;
import auction.service.AccountTablesBuilder;
import auction.service.UserService;


@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountTablesBuilder accountTablesBuilder;
	
	
	@RequestMapping(value="/account")
	public String account(Principal principal){
		
		return "account";
	}
	
	@RequestMapping(value="/account/trading-monitor")
	public String trading_monitor(Model model, Principal principal){
		
		model.addAttribute("tables", accountTablesBuilder
				.init(principal)
				.getTradePools()
				.getFollowers()
				.build());
		
		return "monitor";
	}
	
	@RequestMapping(value="/account/my-items")
	public String myItems(Model model, Principal principal){
		
		model.addAttribute("tables", accountTablesBuilder
				.init(principal)
				.getTradePools()
				.getFollowers()
				.build());
		
		return "my-items";
	}

	@ResponseBody
	@RequestMapping("/account-info")
	public ResponseEntity<?> showItemTradePool(Principal principal) {

		return ResponseEntity
				.ok()
				.body(accountTablesBuilder
						.init(principal)
						.getTradePools()
						.getFollowers()
						.buildJSON());
	}

	@ResponseBody
	@RequestMapping("/account/item-{id}/hide")
	public String doHide(Principal principal, @PathVariable int id) {
	
		return accountService.setHide(principal,id);
	}
	
	@ResponseBody
	@RequestMapping("/account/item-{id}/collapse")
	public String doToggleCollapse(Principal principal, @PathVariable int id) {
	
		return accountService.toggleCollapse(principal,id);
	}
	
}

