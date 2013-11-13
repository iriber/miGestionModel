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

	private String nombreEqual;
	
	private Long nroDocumento;
	
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
	

	public void orderByNombre(String type){
		
		this.addOrder("nombre", type);
	}

	public String getNombreEqual() {
		return nombreEqual;
	}

	public void setNombreEqual(String nombreEqual) {
		this.nombreEqual = nombreEqual;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
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