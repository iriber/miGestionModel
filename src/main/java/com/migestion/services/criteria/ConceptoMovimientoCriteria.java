package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para conceptos de movimiento.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class ConceptoMovimientoCriteria extends Criteria{

	/**
	 * nombre.
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