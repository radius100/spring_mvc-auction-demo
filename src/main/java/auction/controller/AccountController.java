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
	public String account(Model model, Principal principal){
		
		model.addAttribute("tables", accountTablesBuilder
				.init(principal)
				.getTradePools()
				.getFollowers()
				.build());
		//model.addAttribute("tables", accountService.getAccountTablesSkretch(principal));
		
		return "account";
	}
	
	@ResponseBody
	@RequestMapping("/aaccount")
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
	@RequestMapping("/account/item-{id}/expand")
	public String doToggleExpand(Principal principal, @PathVariable int id) {
	
		return accountService.toggleExpand(principal,id);
	}
	
}

