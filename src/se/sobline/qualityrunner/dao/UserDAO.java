package se.sobline.qualityrunner.dao;

import java.util.List;

/***
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

import se.sobline.qualityrunner.model.User;

public interface UserDAO extends CrudDAO<User> {
	
	List<User> getAllUsers();

}
