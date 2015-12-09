package se.sobline.qualityrunner.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @author Charlotte
 * Entity implementation class for Entity: AbstractEntity
 * @since 09.12.2015
 * @version 1.0
 */
@MappedSuperclass

public class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Ignore
	private static final long serialVersionUID = 1L;

	public AbstractEntity() {
		super();
	}   
	public Long getId() {
		return this.Id;
	}
   
}
