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
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */
public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReviewDAO reviewDAO;

	public Controller() {
		userDAO = new JPAUserDAO(factory);
		productDAO = new JPAProductDAO(factory);
		reviewDAO = new JPAReviewDAO(factory);
		initialize();
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

	private void initialize() {
		if ((getProducts() == null) || (getProducts().size() <= 0)) {
			String imgUrl = "http://img1.wikia.nocookie.net/__cb20120204010934/evangelion/images/4/47/Placeholder.png";
			String text = "This is where the description of the product is written. Since the product is not real, "
					+ "this text is just lots of bullshit! But imagine some fancy words trying to sell the product and so on.. "
					+ "Bellow is where you'll fint reviews of this product. Read them, or write your own review.";

			createProduct(new Product("Product One", imgUrl, text));
			createProduct(new Product("Product Two", imgUrl, text));
			createProduct(new Product("Product Three", imgUrl, text));

			createUser("demoUser", "password");

			createReview(new Review(userExists("demoUser"), text, "Pretend Review One", 
					getProduct("Product One")), 5.0);
			createReview(new Review(userExists("demoUser"), text, "Pretend Review Two", 
					getProduct("Product One")), 10.0);
			createReview(new Review(userExists("demoUser"), text, "Pretend Review Three", 
					getProduct("Product Two")), 2.0);
		}
	}

}
