package se.sobline.qualityrunner.dao;

import se.sobline.qualityrunner.model.AbstractEntity;

public interface CrudDAO <E extends AbstractEntity>	{

	E saveOrUpdate(E entity);
	
	E remove(E entity);
	
	E findById(Long id);
}
