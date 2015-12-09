package se.sobline.qualityrunner.dao;

import se.sobline.qualityrunner.model.AbstractEntity;

/***
 * 
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 * @param <E>
 */

public interface CrudDAO <E extends AbstractEntity>	{

	E saveOrUpdate(E entity);
	
	E remove(E entity);
	
	E findById(Long id);
}
