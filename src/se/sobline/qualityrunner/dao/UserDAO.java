package se.sobline.qualityrunner.dao;

import java.util.Collection;
import java.util.List;

import se.sobline.qualityrunner.model.User;

public interface UserDAO extends CrudDAO<User> {
	
	Collection<User> getUsersCollection(String username);
	
	List<User> getAllUsers();

}
