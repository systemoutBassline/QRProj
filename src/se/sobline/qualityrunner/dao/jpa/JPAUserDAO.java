package se.sobline.qualityrunner.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.model.User;

public final class JPAUserDAO extends AbstractJPADAO<User> implements UserDAO {


	public JPAUserDAO(EntityManagerFactory factory) {
		super(User.class, factory);
	}

	@Override
	public List<User> getAllUsers() {
		return query("User.getAll", identity());
	}

}
