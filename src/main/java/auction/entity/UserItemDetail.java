package auction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import auction.entity.User;
import auction.entity.Item;


@Entity
public class UserItemDetail {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="item_id")	
	private Item item;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="user_id")
	private User user;

	private boolean publish;
	
	private boolean follow;
	
	private boolean buy;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	public boolean isBuy() {
		return buy;
	}

	public void setBuy(boolean buy) {
		this.buy = buy;
	}

}
