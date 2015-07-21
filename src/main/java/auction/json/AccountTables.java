package auction.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountTables {

	@Expose
	@SerializedName("Type")
	private String type;
	
	@Expose
	@SerializedName("Id")
	private String itemId;
	
	@Expose
	@SerializedName("Collapsed")
	private boolean collapsed;
	
	@Expose
	@SerializedName("Hidden")
	private boolean hidden;
	
	private String name;
	
	
	public AccountTables(String type, String name, String itemId, boolean collapsed, boolean hidden) {
		
		this.type = type;
		this.name = name;
		this.itemId = itemId;
		this.collapsed=collapsed;
		this.hidden=hidden;
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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

		
}
