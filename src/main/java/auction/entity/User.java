package auction.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import auction.annotation.UniqueUserName;


@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, max = 20)
	@Pattern(regexp="[a-zA-Z]+")
	@UniqueUserName(message="Such user already exist")
	private String name;
	
	@Column(length = 60)
	@Size(min = 5, max = 60)
	private String password;

	@Email
	@Size(min = 5, max = 35)
	private String email;

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	private boolean enabled;
	
	public final boolean isEnabled() {
		return enabled;
	}

	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

/*	
	private Character isBlock;

	public Character getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(Character isBlock) {
		this.isBlock = isBlock;
	}
*/
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
