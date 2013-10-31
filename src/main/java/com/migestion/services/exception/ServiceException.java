package com.migestion.services.exception;

/**
 * Clase genérica para el manejo de excepciones de los servicios.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 */
public class ServiceException extends Exception {

	public ServiceException(){}
	
	public ServiceException(Throwable t){
		
		super(t.getMessage());		
	}

	public ServiceException(String msg){
		
		super(msg);		
	}
}
