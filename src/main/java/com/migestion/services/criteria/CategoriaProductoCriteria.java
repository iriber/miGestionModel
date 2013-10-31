package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para categorías de productos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class CategoriaProductoCriteria extends Criteria{

	/**
	 * nombre del producto.
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
	


}