package se.sobline.qualityrunner.dao.jpa;

import java.util.Collection;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import se.sobline.qualityrunner.dao.CrudDAO;
import se.sobline.qualityrunner.model.AbstractEntity;

public abstract class AbstractJPADAO<E extends AbstractEntity> implements CrudDAO<E> {
	
	private final EntityManagerFactory factory;
	private final Class<E> entityClass;
	
	protected AbstractJPADAO(Class<E> entityClass, EntityManagerFactory factory) {
		this.factory = factory;
		this.entityClass = entityClass;
	}

	@Override
	public E saveOrUpdate(E entity) {
		if(entity.getId() == null) {
			return execute(manager ->{
				manager.persist(entity);
				return entity;
			});
		} else {
			return execute(manager -> manager.merge(entity));
		}
	}

	@Override
	public E remove(E entity) {
		return execute(manager -> {
			E entityToRemove = manager.find(entityClass, entity.getId());
			manager.remove(entityToRemove);
			return entityToRemove;
		});
	}

	@Override
	public E findById(Long id) {
		EntityManager manager = factory.createEntityManager();
		try{
			return manager.find(entityClass, id);
		} finally {
			manager.close();
		}
	}
	
	protected E execute(Function<EntityManager, E> operation) {
		EntityManager manager = factory.createEntityManager();
		try{
			manager.getTransaction().begin();
			E result = operation.apply(manager);
			manager.getTransaction().commit();
			return result;
		} finally {
			manager.close();
		}
	}
	
	protected Collection<E> query(String queryName, Function<TypedQuery <E>, TypedQuery <E>> query) {
		EntityManager manager = factory.createEntityManager();
		try{
			TypedQuery<E> typedQuery = manager.createNamedQuery(queryName, entityClass);
			return query.apply(typedQuery).getResultList();
		} finally {
			manager.close();
		}
	}

}
