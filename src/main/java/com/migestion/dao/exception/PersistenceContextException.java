package com.migestion.dao.exception;

/**
 * Clase genérica para el manejo de excepciones en el persistence context.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 * 
 */
public class PersistenceContextException extends Exception {

	public PersistenceContextException(){}
	
	public PersistenceContextException(Throwable t){
		
		super(t.getMessage());		
	}

	public PersistenceContextException(String msg){
		
		super(msg);		
	}
}
