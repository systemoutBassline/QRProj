package se.sobline.qualityrunner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jdk.nashorn.internal.ir.annotations.Ignore;

/***
 * @author Charlotte & Joel
 * Entity implementation class for Entity: Review
 * @since 09.12.2015
 * @version 1.0
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "Review.getAll", query = "SELECT r FROM Review r"),
		@NamedQuery(name = "Review.getProductReview", query = "SELECT r FROM Review r WHERE r.product.name = :name") })

public class Review extends AbstractEntity {

	@Ignore
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private User user;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String title;

	@ManyToOne(optional = false)
	private Product product;

	protected Review() {

	}

	public Review(User user, String text, String title, Product product) {
		this.user = user;
		this.text = text;
		this.title = title;
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public Product getProduct() {
		return product;
	}

}
