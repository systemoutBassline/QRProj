package se.sobline.qualityrunner.dao.jpa;

import java.util.Collection;

import static java.util.function.Function.identity;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.ProductDAO;
import se.sobline.qualityrunner.model.Product;

public final class JPAProductDAO extends AbstractJPADAO<Product> implements ProductDAO {


	public JPAProductDAO(EntityManagerFactory factory) {
		super(Product.class, factory);
	}

	@Override
	public Collection<Product> getAll() {
		return query("Product.getAll", identity());
	}

}
