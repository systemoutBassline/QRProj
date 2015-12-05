package se.sobline.qualityrunner.controller;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.dao.jpa.JPAUserDAO;
import se.sobline.qualityrunner.model.User;

public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;

	protected Controller() {
		userDAO = new JPAUserDAO(factory);
	}

	public List<User> getUsers() {
		return userDAO.getAllUsers();
	}

	public boolean userExists(String username) {
		for (User user : getUsers()) {
			if (user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public User createUser(String username, String password) {
		return userDAO.saveOrUpdate(new User(username, password));
	}

	public boolean checkLogin(String username, String password) {

		Collection<User> users = userDAO.getAllUsers();

		if (users.contains(username)) {
			if (users.contains(password)) {
				return true;
			}
		}
		return false;
	}
}
