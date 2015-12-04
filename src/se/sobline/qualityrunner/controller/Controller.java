package se.sobline.qualityrunner.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.dao.jpa.JPAUserDAO;

public final class Controller {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("QR");
	private final UserDAO userDAO;
	
	protected Controller() {
		userDAO = new JPAUserDAO(factory);
	}
}
