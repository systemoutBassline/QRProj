package se.sobline.qualityrunner.dao.jpa;

import java.util.Collection;

import static java.util.function.Function.identity;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.model.User;

public final class JPAUserDAO extends AbstractJPADAO<User> implements UserDAO {


	public JPAUserDAO(EntityManagerFactory factory) {
		super(User.class, factory);
	}

	@Override
	public Collection<User>getUser(String username) {
		return query("User.getUser", query -> query.setParameter("username", username));
	}

	@Override
	public Collection<User> getAllUsers() {
		return query("User.getAll", identity());
	}

}
