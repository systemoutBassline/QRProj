package se.sobline.qualityrunner.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

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

public final class FrontController {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReviewDAO reviewDAO;

	/**
	 * Borde den h�r klassen l�ggas upp i sessionen? F�r att man skall ha samma
	 * objekt vilken servlet man �n anv�nder?
	 */
	public FrontController() {

		userDAO = new JPAUserDAO(factory);
		productDAO = new JPAProductDAO(factory);
		reviewDAO = new JPAReviewDAO(factory);
	}

	public Product createProduct(Product product) {
		return productDAO.saveOrUpdate(product);
	}

	public Product getProduct(String productName) {
		for (Product product : getProducts()) {
			if (product.getName().equals(productName)) {
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
		String salt = makeSalt();
		User user = new User(username, encryptThis(password, salt));
		user.setSalt(salt);
		return userDAO.saveOrUpdate(user);
	}


	public List<User> getUsers() {
		return userDAO.getAllUsers();
	}

	public boolean checkLogin(String username, String password) {

		for (User user : getUsers()) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(encryptThis(password, user.getSalt()))) {
					return true;
				}
			}
		}

		return false;
	}

	public Review createReview(Review review) {
		return reviewDAO.saveOrUpdate(mapAndSortReview(review));
	}
	
	// sorting stuff here
	public Review mapAndSortReview(Review review) {
		User user = userExists(review.getUser().getUsername());
		Product product = getProduct(review.getProduct().getName());
		// sorting stuff here
		List<Review> sortedReviews;

		if (!user.getReviews().contains(review)) {
			user.add(review);
			// sorting stuff here
			sortedReviews = sortReviews(user.getReviews());
			user.setReviews(sortedReviews);
			user = updateUser(user);
		}

		if (!product.getReviews().contains(review)) {
			product.add(review);
			// sorting stuff here
			sortedReviews = sortReviews(product.getReviews());
			product.setReviews(sortedReviews);
			product = createProduct(product);
		}
		return review;
	}

	// sorting stuff here
	public List<Review> sortReviews(List<Review> reviews) {
		reviews.sort((review1, review2) -> review1.getGrade().compareTo(review2.getGrade()));
		return reviews;
	}
	
	public List<Review> getReviews() {
		return reviewDAO.getReviews();
	}

	private static String encryptThis(String password, String salt) {
		StringBuffer sb = new StringBuffer();
		try {

			String saltedpassword = password + salt;
			byte[] bytesOfText;
			bytesOfText = saltedpassword.getBytes("UTF-8");

			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			byte[] theDigest = md.digest(bytesOfText);

			for (int i = 0; i < theDigest.length; i++) {
				sb.append(Integer.toHexString((theDigest[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Failed to encrypt ggwp");
		}
		return sb.toString();
	}

	private String makeSalt() {
		char[] chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
