package com.migestion.dao.exception;

/**
 * Clase genérica para el manejo de excepciones en los DAOs.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 */
public class DAOException extends Exception {

	public DAOException(){}
	
	public DAOException(Throwable t){
		
		super(t.getMessage());		
	}

	public DAOException(String msg){
		
		super(msg);		
	}
}
