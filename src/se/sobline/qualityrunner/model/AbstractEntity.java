package se.sobline.qualityrunner.model;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AbstractEntity
 *
 */
@MappedSuperclass

public class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue
	private Long Id;
	private static final long serialVersionUID = 1L;

	public AbstractEntity() {
		super();
	}   
	public Long getId() {
		return this.Id;
	}
   
}
