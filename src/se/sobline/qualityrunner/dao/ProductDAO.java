package se.sobline.qualityrunner.dao;

import java.util.List;

import se.sobline.qualityrunner.model.Product;

/***
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

public interface ProductDAO extends CrudDAO<Product> {

	List<Product> getAll();
	
}
