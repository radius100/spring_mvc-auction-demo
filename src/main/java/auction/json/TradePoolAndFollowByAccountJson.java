package auction.json;

import com.google.gson.annotations.SerializedName;

public class TradePoolAndFollowByAccountJson {

	@SerializedName("Type")
	private String type;
	
	@SerializedName("Id")
	private String itemId;
	
	
	public TradePoolAndFollowByAccountJson(String type, String itemId) {
		
		this.type = type;
		this.itemId = itemId;
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
		
}
