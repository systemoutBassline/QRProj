package se.sobline.qualityrunner.dao;

import java.util.List;

import se.sobline.qualityrunner.model.Review;

public interface ReviewDAO extends CrudDAO<Review> {

	List<Review> getReviews();

}
