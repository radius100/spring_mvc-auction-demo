package auction.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountTablesJson {

	@Expose
	@SerializedName("Type")
	private String type;
	
	@Expose
	@SerializedName("Id")
	private String itemId;
	
	private boolean expand;
	
	private String name;
	
	
	public AccountTablesJson(String type, String name, String itemId, boolean expand) {
		
		this.type = type;
		this.itemId = itemId;
		this.name = name;
		this.expand=expand;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public boolean isExpand() {
		return expand;
	}


	public void setExpand(boolean expand) {
		this.expand = expand;
	}
		
}
