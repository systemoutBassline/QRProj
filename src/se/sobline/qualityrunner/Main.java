package se.sobline.qualityrunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.dao.jpa.JPAProductDAO;
import se.sobline.qualityrunner.dao.jpa.JPAUserDAO;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;
import se.sobline.qualityrunner.model.User;

public class Main {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	
	private static void addData(){

	}
	
	public static void main(String[] args) {
		
		ProductDAO pDAO = new JPAProductDAO(factory);
		UserDAO uDAO = new JPAUserDAO(factory);
		
		addData();
		
		for(Product product : pDAO.getAll()){
			System.out.println(product.getName());
		}
		
		for(User user : uDAO.getAllUsers()) {
			System.out.println(user.getUsername());
		}

	}

}
