package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para cuentas bancarias.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class CuentaBancariaCriteria extends Criteria{

	/**
	 * nombre del banco.
	 */
	private String nombre;

	private String nombreEqual;

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

	public void orderByNombre(String type){
		
		this.addOrder("nombre", type);
	}

	public String getNombreEqual() {
		return nombreEqual;
	}

	public void setNombreEqual(String nombreEqual) {
		this.nombreEqual = nombreEqual;
	}

	public Long getOidNotEqual() {
		return oidNotEqual;
	}

	public void setOidNotEqual(Long oidNotEqual) {
		this.oidNotEqual = oidNotEqual;
	}
}