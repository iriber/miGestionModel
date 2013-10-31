package com.migestion.services.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el manejo de excepciones sobre validaciones.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 */
public class ValidationException extends ServiceException {

	private List<ConstrainMessage> messages;

	public ValidationException(){
		messages = new ArrayList<ConstrainMessage>();
	}
	
	public void addConstrain( ConstrainMessage constrain ){
		messages.add( constrain );
	}

	@Override
	public String getMessage(){
		return showDetails().toString();
	}
	
	public StringBuffer showDetails(){
		StringBuffer sb = new StringBuffer();
		for (ConstrainMessage constrain : messages) {
			sb.append( constrain.getViolation() );			
		}
		return sb;
	}
}
