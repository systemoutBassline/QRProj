package se.sobline.qualityrunner.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.ReviewDAO;
import se.sobline.qualityrunner.model.Review;

/***
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

public final class JPAReviewDAO extends AbstractJPADAO<Review> implements ReviewDAO {

	public JPAReviewDAO(EntityManagerFactory factory) {
		super(Review.class, factory);
	}

	@Override
	public List<Review> getReviews() {
		return query("Review.getAll", identity());
	}
}
