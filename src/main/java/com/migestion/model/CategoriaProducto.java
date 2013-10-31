package com.migestion.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Categoría de Producto
 * 
 * @author bernardo
 * @since 09/10/2013
 */
@Entity
@Table(name="categoria_producto")
public class CategoriaProducto extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636860579527770013L;

	/**
	 * nombre
	 */
	@Column
	@NotNull(message="{categoriaProducto.nombre.required}")
	private String nombre;
	
	/**
	 * descripción
	 */
	@Column
	private String descripcion;

	/**
	 * categoría padre
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="padre_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private CategoriaProducto padre;



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

	/**
	 * @return the padre
	 */
	public CategoriaProducto getPadre() {
		return padre;
	}


	/**
	 * @param padre the padre to set
	 */
	public void setPadre(CategoriaProducto padre) {
		this.padre = padre;
	}


	/**
	 * @return  nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre  nombre a setear
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return  descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion  descripcion a setear
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString(){
		return getNombre();
	}


}
