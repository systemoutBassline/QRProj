package se.sobline.qualityrunner.controller;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.dao.ReviewDAO;
import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.dao.jpa.JPAProductDAO;
import se.sobline.qualityrunner.dao.jpa.JPAReviewDAO;
import se.sobline.qualityrunner.dao.jpa.JPAUserDAO;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;
import se.sobline.qualityrunner.model.User;

public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReviewDAO reviewDAO;

	protected Controller() {
		userDAO = new JPAUserDAO(factory);
		productDAO = new JPAProductDAO(factory);
		reviewDAO = new JPAReviewDAO(factory);	
	}
	
	public Product createProduct(Product product) {
		return productDAO.saveOrUpdate(product);
	}
	
	public Product getProduct(String productName) {
		for(Product product : getProducts()) {
			if(product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
	}

	public List<Product> getProducts() {
		return productDAO.getAll();
	}

	public User updateUser(User user) {
		return userDAO.saveOrUpdate(user);
	}
	
	public User userExists(String username) {
		for (User user : getUsers()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public User createUser(String username, String password) {
		return userDAO.saveOrUpdate(new User(username, password));
	}
	
	public List<User> getUsers() {
		return userDAO.getAllUsers();
	}

	public boolean checkLogin(String username, String password) {

		Collection<User> users = userDAO.getAllUsers();

		if (users.contains(username)) {
			if (users.contains(password)) {
				return true;
			}
		}
		return false;
	}
	
	public Review createReview(Review review) {
		User user = userExists(review.getUser().getUsername());
		
		if(!user.getReviews().contains(review)) {
			user.add(review);
			user = updateUser(user);
		}
		return reviewDAO.saveOrUpdate(review);
	}
}
