package auction.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String descr;

	@Lob
	@Column(length = 500)
	private String fullDescr;

	private Date publishDate;

	private Date startDate;

	private Date finishDate;

	private Integer startAmount;

	private boolean block;

	private boolean active;

	private boolean sell;

	private boolean pay;

	@Transient
	private int currentAmount;

	@Transient
	private int followersCount;

	@Transient
	private int tradersCount;

	@Transient
	private boolean followedByCurrentUser = false;

	@Transient
	private boolean publishedByCurrentUser = false;

	@Transient
	private boolean buyByCurrentUser = false;

	@Transient
	private boolean tradeedByCurrentUser = false;

	@Transient
	private int dateMessage;

	@Transient
	private long dateValue;

	@Transient
	private List<TradePool> tradePools;

	@Transient
	private List<User> followers;

	@Transient
	private List<User> traders;

	@Transient
	private User publisher;

	/*
	 * public List<Image> getImages() { return images; }
	 * 
	 * public void setImages(List<Image> images) { this.images = images; }
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFullDescr() {
		return fullDescr;
	}

	public void setFullDescr(String fullDescr) {
		this.fullDescr = fullDescr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getStartDatetoGMTString() {
		
		if(startDate != null)
			return startDate.toGMTString();
		
		return "";
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public String getFinishDatetoGMTString() {
		
		if(finishDate != null)
			return finishDate.toGMTString();
		
		return "";
	}
	
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getTradersCount() {
		return tradersCount;
	}

	public void setTradersCount(int tradersCount) {
		this.tradersCount = tradersCount;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSell() {
		return sell;
	}

	public void setSell(boolean sell) {
		this.sell = sell;
	}

	public boolean isPay() {
		return pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Date getPublishDate() {
		return publishDate;
	}
	
	public String getPublishDatetoGMTString() {
		
		if(publishDate != null){
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
					//new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
			/*
			 *работает
			 * 14 Jul 2015 12:56:32 GMT
			 * yyyy.MM.dd G 'at' HH:mm:ss z
			 * 
			 * d MMM yyyy HH:mm:ss
			 */
			return dateFormat.format( publishDate );
			//return publishDate.toGMTString();
		}
		return "";
	}
/*
	public String getPublishDatetoGMTString() {
		
		if(publishDate != null)
			return publishDate.;
		
		return "";
	}
*/
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Integer startAmount) {
		this.startAmount = startAmount;
	}

	public long getDateValue() {
		return dateValue;
	}

	public void setDateValue(long dateValue) {
		this.dateValue = dateValue;
	}

	public int getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(int dateMessage) {
		this.dateMessage = dateMessage;
	}

	public List<TradePool> getTradePools() {
		return tradePools;
	}

	public void setTradePools(List<TradePool> tradePools) {
		this.tradePools = tradePools;
	}

	public boolean isFollowedByCurrentUser() {
		return followedByCurrentUser;
	}

	public void setFollowedByCurrentUser(boolean followedByCurrentUser) {
		this.followedByCurrentUser = followedByCurrentUser;
	}

	public boolean isPublishedByCurrentUser() {
		return publishedByCurrentUser;
	}

	public void setPublishedByCurrentUser(boolean publishedByCurrentUser) {
		this.publishedByCurrentUser = publishedByCurrentUser;
	}

	public boolean isBuyByCurrentUser() {
		return buyByCurrentUser;
	}

	public void setBuyByCurrentUser(boolean buyByCurrentUser) {
		this.buyByCurrentUser = buyByCurrentUser;
	}

	public boolean isTradeedByCurrentUser() {
		return tradeedByCurrentUser;
	}

	public void setTradeedByCurrentUser(boolean tradeedByCurrentUser) {
		this.tradeedByCurrentUser = tradeedByCurrentUser;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getTraders() {
		return traders;
	}

	public void setTraders(List<User> traders) {
		this.traders = traders;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

}