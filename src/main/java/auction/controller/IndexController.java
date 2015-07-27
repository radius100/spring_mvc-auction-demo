package auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import auction.builder.ItemListDetailBuilder;
import auction.session.Pagination;

@Controller
@SessionAttributes("pagination")
public class IndexController {

	@ModelAttribute
	public Pagination createPagination(){
		return new Pagination("Active", Direction.DESC, 1, 3);
	}
	
	@Autowired
	private ItemListDetailBuilder itemListDetailBuilder;
	
	
	@RequestMapping("/index")
	public String index(@ModelAttribute Pagination pagination, Model model){

		switch (pagination.getSection()) {
			
			case "Archive": 
				model.addAttribute("items", itemListDetailBuilder
						.getArchiveItemList(pagination)
						.build());
				break;
			
			case "Active": 
				model.addAttribute("items", itemListDetailBuilder
						.getActiveItemList(pagination)
						.build());
				break;

			case "PreTrading": 
				model.addAttribute("items", itemListDetailBuilder
						.getPreTradingItemList(pagination)
						.build());
				break;
			
			case "Trading": 
				model.addAttribute("items", itemListDetailBuilder
						.getTradingItemList(pagination)
						.build());
				break;

		}
		
		return "index";
	}

	@RequestMapping("/trading/index")
	public String indexTradingNow(@ModelAttribute Pagination pagination) {

		pagination.setSection("Trading");

		return "redirect:/index.html";
	}

	@RequestMapping("/publish/index")
	public String indexPreTrading(@ModelAttribute Pagination pagination) {

		pagination.setSection("PreTrading");
		
		return "redirect:/index.html";
	}
	
	@RequestMapping("/all/index")
	public String indexAll(@ModelAttribute Pagination pagination) {

		pagination.setSection("Active");

		return "redirect:/index.html";
	}

	@RequestMapping("/archive/index")
	public String indexArchive(@ModelAttribute Pagination pagination) {

		pagination.setSection("Archive");
		
		return "redirect:/index.html";
	}

	@RequestMapping("/desc/index")
	public String setDesc(@ModelAttribute Pagination pagination) {
		
		pagination.setSortDirection(Direction.DESC);
		
		return "redirect:/index.html";
	}

	@RequestMapping("/asc/index")
	public String setAsc(@ModelAttribute Pagination pagination) {

		pagination.setSortDirection(Direction.ASC);

		return "redirect:/index.html";
	}

	@RequestMapping("/page-{id}/index")
	public String setPage(@ModelAttribute Pagination pagination, @PathVariable int id) {

		if( (id <= pagination.getTotalPages()) && (id > 0) )
			pagination.setPageIndex(id);

		return "redirect:/index.html";
	}

}
