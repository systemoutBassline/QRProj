package se.sobline.qualityrunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.sobline.qualityrunner.controller.Controller;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;
import se.sobline.qualityrunner.model.User;

public class Main {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	
	private static void addData(Controller controller) {

		System.out.println("addData()");

		String imgUrl = "http://img1.wikia.nocookie.net/__cb20120204010934/evangelion/images/4/47/Placeholder.png";
		String text = "Lots of words about the products. bla bla idauwndawiodowadiaihbfeahadwa wd"
				+ "ajdwijadodija wdaoi awdj aojfa owfij aoifj awiof awoifh"
				+ "a dowidj waoidjaw oidjawidjaowfija woidj awoidj owaidjaoiwd jwa";

		String username = "user1", password = "1", username2 = "user2", username3 = "user3";
		String productName = "Product One", productName2 = "Product Two", productName3 = "Product Three";

		System.out.println("--- --- --- USERS --- --- ---");
		if (controller.userExists(username) != null) {
			System.out.println(username + " Occupied");
			User user = controller.userExists(username);
		} else {
			System.out.println(username + " Vacant, creating user with username: " + username);
			User user = controller.createUser(username, password);
			User user2 = controller.createUser(username2, password);
			User user3 = controller.createUser(username3, password);

		}

		System.out.println("--- --- --- PRODUCTS --- --- ---");
		Product productOne = new Product(productName, imgUrl);
		Product productTwo = new Product(productName2, imgUrl);
		Product productThree = new Product("Product Three", imgUrl);

		controller.createProduct(productOne);
		controller.createProduct(productTwo);
		controller.createProduct(productThree);

		System.out.println("--- --- --- REVIEWS --- --- ---");
		Review review1 = null, review2 = null, review3 = null;
		if (controller.getProduct(productName) != null) {
			review1 = new Review(controller.userExists(username), text, "Review 1 Title",
					controller.getProduct(productName));
			review1 = controller.createReview(review1, (double)0.0);
		}
		if (controller.getProduct(productName2) != null) {
			review2 = new Review(controller.userExists(username), text, "Review 2 Title",
					controller.getProduct(productName2));
			review2 = controller.createReview(review2, (double)1.1);
		}
		if (controller.getProduct(productName) != null) {
			review3 = new Review(controller.userExists(username2), text, "Review 3 Title",
					controller.getProduct(productName));
			review3 = controller.createReview(review3, (double)2.2);
		}

		System.out.println("USER:");
		System.out.println("id: " + controller.userExists(username).getId() 
				+ ", username: " + controller.userExists(username).getUsername() 
				+ ", password: " + controller.userExists(username).getPassword()
				+ ", reviewlist size: " + controller.userExists(username).getReviews().size());
		System.out.println("id: " + controller.userExists(username2).getId() 
				+ ", username: " + controller.userExists(username2).getUsername() 
				+ ", password: " + controller.userExists(username2).getPassword()
				+ ", reviewlist size: " + controller.userExists(username2).getReviews().size());
		System.out.println("id: " + controller.userExists(username3).getId() 
				+ ", username: " + controller.userExists(username3).getUsername() 
				+ ", password: " + controller.userExists(username3).getPassword()
				+ ", reviewlist size: " + controller.userExists(username3).getReviews().size());

		System.out.println("PRODUCT:");
		System.out.println("id: " + controller.getProduct(productName).getId()
				+ ", name: " + controller.getProduct(productName).getName()
				+ ", grade: " + controller.getProduct(productName).getGrade()
				+ ", imgurl: " + controller.getProduct(productName).getImgURL()
				+ ", reviewlist size: " + controller.getProduct(productName).getReviews().size());
		System.out.println("id: " + controller.getProduct(productName2).getId()
				+ ", name: " + controller.getProduct(productName2).getName()
				+ ", grade: " + controller.getProduct(productName2).getGrade()
				+ ", imgurl: " + controller.getProduct(productName2).getImgURL()
				+ ", reviewlist size: " + controller.getProduct(productName2).getReviews().size());
		System.out.println("id: " + controller.getProduct(productName3).getId()
				+ ", name: " + controller.getProduct(productName3).getName()
				+ ", grade: " + controller.getProduct(productName3).getGrade()
				+ ", imgurl: " + controller.getProduct(productName3).getImgURL()
				+ ", reviewlist size: " + controller.getProduct(productName3).getReviews().size());
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		addData(controller);
	}
	
}
