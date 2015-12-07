package se.sobline.qualityrunner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Review.getProductReview", query = "SELECT r FROM Review r WHERE r.product.name = :name")
public class Review extends AbstractEntity {
	
	@ManyToOne(optional = false)
	private User user;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private int grade;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne(optional = false)
	private Product product;
	
	protected Review() {
		
	}

	public Review(User user, String text, int grade, String title, Product product) {
		this.user = user;
		this.text = text;
		this.grade = grade;
		this.title = title;
		this.product = product;
	}
	
	public String getText() {
		return text;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Product getProduct() {
		return product;
	}

	public User getUser() {
		return user;
	}

}
