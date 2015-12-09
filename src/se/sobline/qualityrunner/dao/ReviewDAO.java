package se.sobline.qualityrunner.dao;

import java.util.List;

/***
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

import se.sobline.qualityrunner.model.Review;

public interface ReviewDAO extends CrudDAO<Review> {

	List<Review> getReviews();

}
