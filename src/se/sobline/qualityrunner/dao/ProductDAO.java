package se.sobline.qualityrunner.dao;

import java.util.Collection;

import se.sobline.qualityrunner.model.Product;

public interface ProductDAO extends CrudDAO<Product> {

	Collection<Product> getAll();
	
}
