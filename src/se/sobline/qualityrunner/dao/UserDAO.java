package se.sobline.qualityrunner.dao;

import java.util.Collection;

import se.sobline.qualityrunner.model.User;

public interface UserDAO extends CrudDAO<User> {
	
	Collection<User> getUser(String username);
	
	Collection<User> getAllUsers();

}
