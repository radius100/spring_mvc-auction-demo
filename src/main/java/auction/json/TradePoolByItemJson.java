package auction.json;

import com.google.gson.annotations.SerializedName;

public class TradePoolByItemJson {

	@SerializedName("User")
	private String userName;
	
	@SerializedName("Amount")
	private int amount;
	
	@SerializedName("Date")
	private String messageDate;
	
	@SerializedName("Time")
	private String messageTime;

	public TradePoolByItemJson(String userName, int amount, String messageDate, String messageTime){
		this.userName = userName;
		this.amount = amount;
		this.messageDate = messageDate;
		this.messageTime = messageTime;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
