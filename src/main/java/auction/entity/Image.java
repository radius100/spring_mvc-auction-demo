package auction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private byte[] body;

//	@ManyToOne
//	@JoinTable(name="item_id")
//	private Item item;
	
	private Boolean avatarBool;

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

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
/*
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
*/
	public Boolean isAvatar() {
		return avatarBool;
	}

	public void setAvatar(Boolean avatar) {
		this.avatarBool = avatar;
	}
	
	
}
