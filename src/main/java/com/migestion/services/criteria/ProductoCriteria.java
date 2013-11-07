package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para productos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class ProductoCriteria extends Criteria{

	/**
	 * nombre del producto.
	 */
	private String nombre;

	private String nombreEqual;
	
	private Long oid;
	
	private Long oidNotEqual;
	
	public Long getOidNotEqual() {
		return oidNotEqual;
	}

	public void setOidNotEqual(Long oidNotEqual) {
		this.oidNotEqual = oidNotEqual;
	}

	public String getNombreEqual() {
		return nombreEqual;
	}

	public void setNombreEqual(String nombreEqual) {
		this.nombreEqual = nombreEqual;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

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