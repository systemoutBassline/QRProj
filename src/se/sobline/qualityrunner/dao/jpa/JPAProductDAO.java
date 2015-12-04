package se.sobline.qualityrunner.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.model.Product;

public final class JPAProductDAO extends AbstractJPADAO<Product> implements ProductDAO {

	public JPAProductDAO(EntityManagerFactory factory) {
		super(Product.class, factory);
		// adding products for testing purposes
		if (getAll().isEmpty()) {
			addTempProducts();
		}
	}

	// method for testing purposes only, to be removed
	private void addTempProducts() {
		String imgUrl = "http://img1.wikia.nocookie.net/__cb20120204010934/evangelion/images/4/47/Placeholder.png";
		Product productOne = new Product("Product One", 8, imgUrl);
		Product productTwo = new Product("Product Two", 9, imgUrl);
		Product productThree = new Product("Product Three", 10, imgUrl);

		saveOrUpdate(productOne);
		saveOrUpdate(productTwo);
		saveOrUpdate(productThree);
	}

	@Override
	public List<Product> getAll() {
		return queryList("Product.getAll", identity());
	}
}
