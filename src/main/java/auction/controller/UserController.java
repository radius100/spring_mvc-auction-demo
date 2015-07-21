package auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import auction.entity.User;
import auction.service.UserItemDetailService;
import auction.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserItemDetailService userItemDetailService;
	
	@ModelAttribute("user")
	public User constract(){
		return new User();
	}
	
	@RequestMapping("/users")
	public String users(Model model){
		
		model.addAttribute("users", userService.getAll());
		
		return "users";
	}
	
	@RequestMapping("/users/user-{id}")
	public String detail(Model model, @PathVariable int id){
	
		User user = userService.getOne(id);

		model.addAttribute("user", user);
//		model.addAttribute("publishs", userItemDetailService.findUserItemsPublish(user));	
//		model.addAttribute("follows", userItemDetailService.findUserItemsFollow(user));

		return "user-item-detail";
	}
	
	@RequestMapping("/user/register")
	public String showRegister(){
		
		return "user-register";
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user){
		
		userService.save(user);
		return "redirect:/user/register.html?success=true";
	}

}