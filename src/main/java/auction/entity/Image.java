package auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 250000)
	private byte[] body;
	
	private String contentType;

	@ManyToOne(optional = false)
	@JoinColumn(name="item_id")	
	private Item item;

	private boolean avatarBool;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isAvatarBool() {
		return avatarBool;
	}

	public void setAvatarBool(boolean avatarBool) {
		this.avatarBool = avatarBool;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
