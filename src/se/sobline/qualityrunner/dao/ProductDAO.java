package se.sobline.qualityrunner.dao;

import java.util.List;

import se.sobline.qualityrunner.model.Product;

public interface ProductDAO extends CrudDAO<Product> {

	List<Product> getAll();
	
}
