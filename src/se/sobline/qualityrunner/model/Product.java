package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * Entity implementation class for Entity: Product
 *
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
<<<<<<< HEAD
=======
	}
	
	public void add(Review review) {
		reviews.add(review);
>>>>>>> refs/remotes/origin/ninja
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
<<<<<<< HEAD

	public List<Review> getReviews() {
		return reviews;
	}
   
=======
	public List<Review> getReviews() {
		return reviews;
	}
>>>>>>> refs/remotes/origin/ninja
}
