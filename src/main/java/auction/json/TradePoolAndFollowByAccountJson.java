package auction.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradePoolAndFollowByAccountJson {

	@Expose
	@SerializedName("Type")
	private String type;
	
	@Expose
	@SerializedName("Id")
	private String itemId;
	
	private String name;
	
	
	public TradePoolAndFollowByAccountJson(String type, String name, String itemId) {
		
		this.type = type;
		this.itemId = itemId;
		this.name = name;
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
		
}
