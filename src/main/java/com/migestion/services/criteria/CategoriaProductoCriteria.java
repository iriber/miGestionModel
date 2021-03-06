package com.migestion.services.criteria;

import com.migestion.model.CategoriaProducto;

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

	private Boolean sinPadre=null;
	
	private CategoriaProducto padre;

	private String nombreEqual;
	
	private Long oid;
	
	private Long oidNotEqual;
	
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

	public Boolean getSinPadre() {
		return sinPadre;
	}

	public void setSinPadre(Boolean sinPadre) {
		this.sinPadre = sinPadre;
	}

	public CategoriaProducto getPadre() {
		return padre;
	}

	public void setPadre(CategoriaProducto padre) {
		this.padre = padre;
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

	public Long getOidNotEqual() {
		return oidNotEqual;
	}

	public void setOidNotEqual(Long oidNotEqual) {
		this.oidNotEqual = oidNotEqual;
	}
	


}