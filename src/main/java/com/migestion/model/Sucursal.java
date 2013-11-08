package com.migestion.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Representa una sucursal de la empresa/comerciante.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="sucursal")
public class Sucursal extends GenericEntity{

	/**
	 * nombre
	 */
	@Column
	@NotNull
	private String nombre;

	/**
	 * número 
	 */
	@Column
	private String numero;

	/**
	 * teléfono 
	 */
	@Column
	private String telefono;

	/**
	 * domicilio 
	 */
	@Column
	private String domicilio;

	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	
	
	/**
	 * identificador de la entity
	 */
	@Id @GeneratedValue
	@Column
	private Long oid;


	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}


	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public Sucursal(){
		
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



	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}



	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}



	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}



	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}



	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}



	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}



	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String toString(){
		return this.getNombre();
	}

}