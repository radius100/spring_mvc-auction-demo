package auction.session;

import org.springframework.data.domain.Sort.Direction;

public class Pagination {

	private String section;
	private int pageIndex;
	private int itemsPerPage;
	private Direction sortDirection;
	
	private boolean init;
	
	public Pagination(String section, Direction desc, int pageIndex, int itemsPerPage){
		
		this.section = section;
		this.sortDirection = desc;
		this.pageIndex = pageIndex;
		this.itemsPerPage = itemsPerPage;
	}
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public Direction getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Direction sortDirection) {
		this.sortDirection = sortDirection;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

}
