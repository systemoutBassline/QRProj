package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;
import se.sobline.qualityrunner.model.AbstractEntity;

/**
 * Entity implementation class for Entity: User
 *
 */
@NamedQueries(value = {
		@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.getUser", query = "SELECT u FROM User u WHERE u.username = :username")
})
@Table(name ="USERS", schema = "QR")
@Entity
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
//	@ManyToOne(optional = true)
	private Collection<Review> reviews;

	protected User() {
		super();
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.reviews = new HashSet<>();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
   
	public void setPassword(String password) {
		this.password = password;
	}
}
