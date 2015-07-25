package auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import auction.builder.ItemListDetailBuilder;
import auction.session.Pagination;

@Controller
@SessionAttributes("pagination")
public class IndexController {

	@ModelAttribute
	public Pagination createPagination(){
		return new Pagination("All", Direction.DESC, 0, 3);
	}
	
	@Autowired
	private ItemListDetailBuilder itemListDetailBuilder;
	

	@RequestMapping("/index")
	public String index(@ModelAttribute Pagination pagination, Model model, @RequestParam String sort){
		
		if(sort.equals("DESC"))
			pagination.setSortDirection(Direction.DESC);
		else if(sort.equals("ASC"))
			pagination.setSortDirection(Direction.ASC);
		
		if( !pagination.isInit() ){
			pagination.setInit(true);

			model.addAttribute("items", itemListDetailBuilder.getQuery(pagination).build());
		}
		
		model.addAttribute("nav", pagination.getSortDirection());

		return "index";
	}

	@RequestMapping("/trading/index")
	public String indexTradingNow(@ModelAttribute Pagination pagination, Model model) {

		pagination.setSection("Trading");
		model.addAttribute("items", itemListDetailBuilder.getQuery(pagination).build());

		return "forward:/index.html";
	}

	@RequestMapping("/publish/index")
	public String indexPreTrading(@ModelAttribute Pagination pagination, Model model) {

		pagination.setSection("PreTrading");
		model.addAttribute("items", itemListDetailBuilder.getQuery(pagination).build());
		
		return "forward:/index.html";
	}
	
	@RequestMapping("/all/index")
	public String indexAll(@ModelAttribute Pagination pagination, Model model) {

		pagination.setSection("All");
		model.addAttribute("items", itemListDetailBuilder.getQuery(pagination).build());
		
		return "forward:/index.html";
	}

	@RequestMapping("/history/index")
	public String indexHistory(@ModelAttribute Pagination pagination, Model model) {

		pagination.setSection("History");
		model.addAttribute("items", itemListDetailBuilder.getQuery(pagination).build());
		
		return "forward:/index.html";
	}
	
}
