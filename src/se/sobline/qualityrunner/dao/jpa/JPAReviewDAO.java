package se.sobline.qualityrunner.dao.jpa;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.ReviewDAO;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;

public final class JPAReviewDAO extends AbstractJPADAO<Review> implements ReviewDAO {

	public JPAReviewDAO(EntityManagerFactory factory) {
		super(Review.class, factory);
		
	}
}
