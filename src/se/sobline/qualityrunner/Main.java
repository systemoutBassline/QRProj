package se.sobline.qualityrunner;

import se.sobline.qualityrunner.controller.Controller;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;
import se.sobline.qualityrunner.model.User;

public class Main {

	private static void addData(Controller controller) {

		System.out.println("addData()");

		String imgUrl = "http://img1.wikia.nocookie.net/__cb20120204010934/evangelion/images/4/47/Placeholder.png";
		String text = "Lots of words about the products. bla bla idauwndawiodowadiaihbfeahadwa wd"
				+ "ajdwijadodija wdaoi awdj aojfa owfij aoifj awiof awoifh"
				+ "a dowidj waoidjaw oidjawidjaowfija woidj awoidj owaidjaoiwd jwa";

		String username = "user3", password = "3", username2 = "user2";
		String productName = "Product One", productName2 = "Product Two";

		System.out.println("--- --- --- USERS --- --- ---");
		if (controller.userExists(username) != null) {
			System.out.println(username + " Occupied");
			User user = controller.userExists(username);
			System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword());
		} else {
			System.out.println(username + " Vacant, creating user with username: " + username);
			User user = controller.createUser(username, password);
			System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword());
		}

		System.out.println("--- --- --- PRODUCTS --- --- ---");
		Product productOne = new Product(productName, 8, imgUrl);
		Product productTwo = new Product(productName2, 9, imgUrl);
		Product productThree = new Product("Product Three", 10, imgUrl);

		controller.createProduct(productOne);
		controller.createProduct(productTwo);
		controller.createProduct(productThree);

		System.out.println("--- --- --- REVIEWS --- --- ---");
		Review review1, review2, review3;
		if (controller.getProduct(productName) != null) {
			review1 = new Review(controller.userExists(username), text, 1, "Review 1 Title",
					controller.getProduct(productName));
			review1 = controller.createReview(review1);
			productOne = controller.createProduct(productOne);
			productOne.add(review1);
			productOne = controller.createProduct(productOne);
			System.out.println(review1.getTitle() + " added to product: " + productOne.getName() 
					+ ". reviews.size(" + productOne.getReviews().size());
		}
		if (controller.getProduct(productName2) != null) {
			review2 = new Review(controller.userExists(username), text, 2, "Review 2 Title",
					controller.getProduct(productName2));
			review2 = controller.createReview(review2);
			productTwo = controller.getProduct(productName2);
			productTwo.add(review2);
			productTwo = controller.createProduct(productTwo);
			System.out.println(review2.getTitle() + " added to product: " + productTwo.getName() 
			+ ". reviews.size(" + productTwo.getReviews().size());
		}
		if (controller.getProduct(productName) != null) {
			review3 = new Review(controller.userExists(username2), text, 3, "Review 3 Title",
					controller.getProduct(productName));
			review3 = controller.createReview(review3);
			productOne = controller.createProduct(productOne);
			productOne.add(review3);
			productOne = controller.createProduct(productOne);
			System.out.println(review3.getTitle() + " added to product: " + productOne.getName() 
					+ ". reviews.size(" + productOne.getReviews().size());
		}
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		addData(controller);
	}

}
