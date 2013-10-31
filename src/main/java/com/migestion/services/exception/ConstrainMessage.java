package com.migestion.services.exception;

import java.io.Serializable;

/**
 * Clase para manejar los mensajes de las constrains.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 */
public class ConstrainMessage implements Serializable{

	private String violation;
	private String entity;
	private String embedable;
	private String attribute;
	private String invalidValue;

	public ConstrainMessage(String violation, String entity, String embedable, String attribute, String invalidValue){
		this.violation = violation;
		this.entity = entity;
		this.embedable = embedable;
		this.attribute = attribute;
		this.invalidValue = invalidValue;
	}
	
	
	public String getEmbedable() {
		return embedable;
	}


	public void setEmbedable(String embedable) {
		this.embedable = embedable;
	}


	public String getViolation() {
		return violation;
	}

	public void setViolation(String violation) {
		this.violation = violation;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Object getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(String invalidValue) {
		this.invalidValue = invalidValue;
	}


}
