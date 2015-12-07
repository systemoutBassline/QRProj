package se.sobline.qualityrunner.dao;

import java.util.List;

import se.sobline.qualityrunner.model.User;

public interface UserDAO extends CrudDAO<User> {
	
	List<User> getAllUsers();

}
