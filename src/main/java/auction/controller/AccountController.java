package auction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auction.service.AccountService;
import auction.service.UserService;


@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/account")
	public String account(Model model, Principal principal){
		
		model.addAttribute("tables", accountService.getTradePoolAndFollowByAccount(principal));
		
		return "account";
	}
	
	@ResponseBody
	@RequestMapping("/aaccount")
	public ResponseEntity<?> showItemTradePool(Principal principal) {
		
		/*
		 * [{{"method":"Follow"},{"id":"2"}},{{"method":"Trade"},{"id":"3"}}]
		 * 
		 * [  { "Method": "follow", "ItemId": "3" }, { "Method": "trade", "ItemId": "2" } ]
		 * 
		 * Удалить follow если trade=true
		 */

		return ResponseEntity
				.ok()
				.body(accountService.getTradePoolAndFollowByAccountJson(principal));
	}

}

