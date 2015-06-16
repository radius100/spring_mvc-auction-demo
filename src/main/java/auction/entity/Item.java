package auction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String descr;

	private String fullDescr;

	private Date publishDate;
	
	private Date startDate;

	private Integer startAmount;

	private Date finishDate;

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
	private Boolean followedByCurrentUser = false;

	@Transient
	private Boolean tradeedByCurrentUser = false;

	@Transient
	private String dateMessage;

	
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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
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

	public Boolean getFollowedByCurrentUser() {
		return followedByCurrentUser;
	}

	public void setFollowedByCurrentUser(Boolean followedByCurrentUser) {
		this.followedByCurrentUser = followedByCurrentUser;
	}

	public Boolean getTradeedByCurrentUser() {
		return tradeedByCurrentUser;
	}

	public void setTradeedByCurrentUser(Boolean tradeedByCurrentUser) {
		this.tradeedByCurrentUser = tradeedByCurrentUser;
	}

	public String getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(String dateMessage) {
		this.dateMessage = dateMessage;
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

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Integer startAmount) {
		this.startAmount = startAmount;
	}

}
