package se.sobline.qualityrunner.controller;

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

public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReviewDAO reviewDAO;
	/**
	 * Borde den här klassen läggas upp i sessionen? För att man skall ha samma objekt vilken
	 * servlet man än använder?
	 */
	protected Controller() {
		userDAO = new JPAUserDAO(factory);
		productDAO = new JPAProductDAO(factory);
		reviewDAO = new JPAReviewDAO(factory);
	}
//
//	public boolean userExists(String username) {
//
//		Collection<User> users = userDAO.getAllUsers();
//		System.out.println("inside userExists");
//		if(users.contains(username)) {
//			return true;
//		}
//		return false;
//	}

	public List<Product> getAllProducts() {
		return productDAO.getAll();
	}
}
