package auction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auction.service.AccountMonitorTablesBuilder;
import auction.service.AccountMyItemsTablesBuilder;
import auction.service.AccountService;
import auction.service.UserService;


@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountMonitorTablesBuilder accountMonitorTablesBuilder;
	
	@Autowired
	private AccountMyItemsTablesBuilder accountMyItemsTablesBuilder;

	
	
	@RequestMapping(value="/account")
	public String accountProfile(Model model, Principal principal){
		
		model.addAttribute("myItems", accountService.getMyItemSettings(principal));
		model.addAttribute("monitoringItems", accountService.getTradingMonitorSettings(principal));

		return "account-profile";
	}
	
	@RequestMapping(value="/account/trading-monitor")
	public String trading_monitor(Model model, Principal principal){
		
		model.addAttribute("tables", accountMonitorTablesBuilder
				.init(principal)
				.getTradePools()
				.getFollowers()
				.build());
		
		return "monitor";
	}
	
	@RequestMapping(value="/account/my-items")
	public String myItems(Model model, Principal principal){
		
		model.addAttribute("tables", accountMyItemsTablesBuilder
				.init(principal)
				.getTradePoolsActive()
				.build());
		
		return "my-items";
	}

	@ResponseBody
	@RequestMapping("/account-info-my-items")
	public ResponseEntity<?> showMyItemsTradePool(Principal principal) {

		return ResponseEntity
				.ok()
				.body(accountMyItemsTablesBuilder
						.init(principal)
						.getTradePoolsActive()
						.buildJSON());
	}

	@ResponseBody
	@RequestMapping("/account-info-monitor")
	public ResponseEntity<?> showMonitorTradePool(Principal principal) {

		return ResponseEntity
				.ok()
				.body(accountMonitorTablesBuilder
						.init(principal)
						.getTradePools()
						.getFollowers()
						.buildJSON());
	}

	@ResponseBody
	@RequestMapping("/account/item-{id}/hide")
	public String doToggleHide(Principal principal, @PathVariable int id) {
	
		return accountService.toggleHide(principal,id);
	}
	
	@ResponseBody
	@RequestMapping("/account/item-{id}/collapse")
	public String doToggleCollapse(Principal principal, @PathVariable int id) {
	
		return accountService.toggleCollapse(principal,id);
	}
	
}

