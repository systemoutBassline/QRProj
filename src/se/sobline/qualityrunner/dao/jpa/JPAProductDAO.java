package se.sobline.qualityrunner.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.model.Product;

/***
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

public final class JPAProductDAO extends AbstractJPADAO<Product> implements ProductDAO {

	public JPAProductDAO(EntityManagerFactory factory) {
		super(Product.class, factory);
	}

	@Override
	public List<Product> getAll() {
		return query("Product.getAll", identity());
	}
}
