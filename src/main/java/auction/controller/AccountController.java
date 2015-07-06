package auction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import auction.service.UserService;


@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/account")
	public String account(Model model, Principal principal){
		
		//String name = principal.getName();
		//model.addAttribute("", arg1)
		return "account";
	}

}

