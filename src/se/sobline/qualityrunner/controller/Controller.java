package se.sobline.qualityrunner.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.dao.ReviewDAO;
import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.dao.jpa.JPAProductDAO;
import se.sobline.qualityrunner.dao.jpa.JPAReviewDAO;
import se.sobline.qualityrunner.dao.jpa.JPAUserDAO;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;
import se.sobline.qualityrunner.model.User;

/***
 * Class that ties the database and the view together
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */
public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReviewDAO reviewDAO;

	/**
	 * Borde den här klassen läggas upp i sessionen? För att man skall ha samma
	 * objekt vilken servlet man än använder?
	 */
	public Controller() {

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

	public Review createReview(Review review, double grade) {
		return reviewDAO.saveOrUpdate(mapReview(review, grade));
	}
	
	// sorting stuff here
	public Review mapReview(Review review, double grade) {
		User user = userExists(review.getUser().getUsername());
		Product product = getProduct(review.getProduct().getName());

		if (!user.getReviews().contains(review)) {
			user.add(review);
			user = updateUser(user);
		}

		if (!product.getReviews().contains(review)) {
			product.add(review);
			product.setGrade(grade);
			System.out.println("fkn grade this shit.." + grade);
			product = createProduct(product);
		}
		return review;
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
	
	 public String cleanInput(String productInput) {
		  productInput = Jsoup.clean(productInput, Whitelist.none().removeAttributes(":all", "class"));
		  return productInput;
		 }
}
