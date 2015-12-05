package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p")
public class Product extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int grade;
	
	@Column
	private String imgURL;
	
	@OneToMany
	private Collection<Review> reviews;
	
	protected Product() {
		super();
	}
	
	public Product(String name, int grade, String imgURL) {
		this.name = name;
		this.grade = grade;
		this.imgURL = imgURL;
	}
	
	public void add(Review review) {
		reviews.add(review);
	}
	
	public String getName() {
		return name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public String getImgURL() {
		return imgURL;
	}
	public Collection<Review> getReviews() {
		return reviews;
	}
}
