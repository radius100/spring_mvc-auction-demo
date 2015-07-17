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
	
	@Expose
	@SerializedName("Collapsed")
	private boolean collapsed;
	
	private String name;
	
	
	public AccountTablesJson(String type, String name, String itemId, boolean collapsed) {
		
		this.type = type;
		this.name = name;
		this.itemId = itemId;
		this.collapsed=collapsed;
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

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

		
}
