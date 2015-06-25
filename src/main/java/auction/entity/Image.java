package auction.entity;

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
	
	private String name;
	
	private String contentType;
	
	private long size;
	
	private byte[] body;

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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
