package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import jdk.nashorn.internal.ir.annotations.Ignore;

/*** 
 * @author Charlotte & Joel
 * Entity implementation class for Entity: Product
 * @since 09.12.2015
 * @version 1.0
 */
@Entity
@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p")
public class Product extends AbstractEntity implements Serializable {

	@Ignore
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int grade;

	@Column
	private String imgURL;

	@OneToMany
	private List<Review> reviews;

	protected Product() {
		super();
	}

	public Product(String name, int grade, String imgURL) {
		this.name = name;
		this.grade = grade;
		this.imgURL = imgURL;
		this.reviews = new ArrayList<>();
	}

	public Review add(Review review) {
		reviews.add(review);
		return review;
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
