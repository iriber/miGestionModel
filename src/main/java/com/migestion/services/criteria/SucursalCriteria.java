package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para sucursales.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class SucursalCriteria extends Criteria{

	/**
	 * nombre de la sucursal.
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