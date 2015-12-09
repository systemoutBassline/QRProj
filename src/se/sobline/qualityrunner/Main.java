package se.sobline.qualityrunner;

import se.sobline.qualityrunner.controller.Controller;
import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.User;

public class Main {

	private static void addData(Controller controller) {

		System.out.println("addData()");

		String imgUrl = "http://img1.wikia.nocookie.net/__cb20120204010934/evangelion/images/4/47/Placeholder.png";
		String text = "This is where the description of the product is written. Since the product is not real, "
				+ "this text is just lots of bullshit! But imagine some fancy words trying to sell the product and so on.. "
				+ "Bellow is where you'll fint reviews of this product. Read them, or write your own review.";

		String username = "testUserOne", password = "1", username2 = "testUserTwo";
		String productName = "Product One", productName2 = "Product Two", productName3 = "Product Three";

		System.out.println("--- --- --- USERS --- --- ---");
		User user = controller.createUser(username, password);
		User user2 = controller.createUser(username2, password);

		System.out.println("--- --- --- PRODUCTS --- --- ---");
		Product productOne = new Product(productName, imgUrl, text);
		Product productTwo = new Product(productName2, imgUrl, text);
		Product productThree = new Product(productName3, imgUrl, text);

		controller.createProduct(productOne);
		controller.createProduct(productTwo);
		controller.createProduct(productThree);
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		addData(controller);
	}

}
