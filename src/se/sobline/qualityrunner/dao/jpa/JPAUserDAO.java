package se.sobline.qualityrunner.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.sobline.qualityrunner.dao.UserDAO;
import se.sobline.qualityrunner.model.User;

/***
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */

public final class JPAUserDAO extends AbstractJPADAO<User> implements UserDAO {


	public JPAUserDAO(EntityManagerFactory factory) {
		super(User.class, factory);
	}

	@Override
	public List<User> getAllUsers() {
		return query("User.getAll", identity());
	}

}
