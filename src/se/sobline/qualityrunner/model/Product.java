package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jdk.nashorn.internal.ir.annotations.Ignore;

/*** 
 * @author Charlotte & Joel
 * Entity implementation class for Entity: Product
 * @since 09.12.2015
 * @version 1.0
 */
@Entity
@Table(name = "PRODUCTS", schema = "QR")
@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p")
public class Product extends AbstractEntity implements Serializable {

	@Ignore
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private double grade;

	@Column(nullable = false)
	private double totalGrade;

	@Column(nullable = false)
	private long totalNumberOfGrades;

	@Column
	private String imgURL;

	@OneToMany
	private List<Review> reviews;

	protected Product() {
		super();
	}

	public Product(String name, String imgURL, String text) {
		this.name = name;
		this.text = text;
		this.totalGrade = 0.0;
		this.totalNumberOfGrades = 0L;
		this.grade = 0.0;
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

	public double getGrade() {
		grade = Math.round((grade * 100.00) / 100.00);
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

	public double setGrade(Double grade) {
		totalNumberOfGrades++;
		totalGrade = totalGrade + grade;
		this.grade = totalGrade / totalNumberOfGrades;
		return this.grade;
	}
	
	public String getText() {
		return text;
	}
}
