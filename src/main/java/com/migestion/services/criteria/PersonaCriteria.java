package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para personas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class PersonaCriteria extends Criteria{

	/**
	 * nombre de la persona.
	 */
	private String nombre;

	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public void orderByNombre(String type){
		
		this.addOrder("nombre", type);
	}
}