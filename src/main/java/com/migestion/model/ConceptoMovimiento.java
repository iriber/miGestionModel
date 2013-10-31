package com.migestion.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Representa un concepto de movimiento.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */

@Entity
@Table(name="concepto_movimiento")
public class ConceptoMovimiento extends GenericEntity{

	/**
	 * nombre
	 */
	@Column
	@NotNull(message="{conceptoMovimiento.nombre.required}")
	private String nombre;
	
	/**
	 * descripción 
	 */
	@Column
	private String descripcion;
	
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

	public ConceptoMovimiento(){
		
	}


	public String toString(){
		return getNombre();
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}