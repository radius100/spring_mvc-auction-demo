package auction.session;

import org.springframework.data.domain.Sort.Direction;

public class Pagination {

	private String section;
	private int pageIndex;
	private int itemsPerPage;
	private int totalPages;
	private Direction sortDirection;
	
	private int[] gridArray = new int[10];
	private int prevPage;
	private int nextPage;
	
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

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setGrid(int totalLines) {
		
		totalPages = totalLines / itemsPerPage;
		
		if( (totalLines % itemsPerPage) != 0 )
			totalPages++; 
	
		if(totalPages <= 10){
			
			for(int i = 0; i < totalPages; i++){
				gridArray[i] = (i+1);
			}
		}
	
		prevPage = pageIndex-1;
		nextPage = pageIndex+1;
		
		if(prevPage < 1 )
			prevPage=-1;
		
		if(nextPage > totalPages)
			nextPage=-1;
	}

	public int[] getGridArray() {
		return gridArray;
	}

	public void setGridArray(int[] gridArray) {
		this.gridArray = gridArray;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

}
