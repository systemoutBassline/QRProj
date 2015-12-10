package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @author Charlotte & Joel Entity implementation class for Entity: User
 * @since 09.12.2015
 * @version 1.0
 */
@NamedQueries(value = { @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.getUser", query = "SELECT u FROM User u WHERE u.username = :username") })
@Table(name = "USERS", schema = "QUALITYRUNNER")
@Entity
public class User extends AbstractEntity implements Serializable {

	@Ignore
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String salt;

	@OneToMany
	private List<Review> reviews;

	protected User() {
		super();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.reviews = new ArrayList<>();
	}

	public Review add(Review review) {
		reviews.add(review);
		return review;
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}
}
