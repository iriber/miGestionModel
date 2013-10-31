package com.migestion.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Producto
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 09/10/2013
 * 
 */
@Entity
@Table(name="producto")
public class Producto extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 89401136515910508L;

	/**
	 * nombre
	 */
	@Column
	@NotNull(message="{producto.nombre.required}")
	private String nombre;
	
	/**
	 * descripción
	 */
	@Column
	private String descripcion;

	/**
	 * observaciones
	 */
	@Column(length = 65535,columnDefinition="Text")
	private String observaciones;
	
	/**
	 * código de barras del producto.
	 */
	@Column
	private String codigoBarras;

	/**
	 * stock actual del producto.
	 */
	@Column
	private Integer stockActual;

	/**
	 * stock mínimo del producto.
	 */
	@Column
	private Integer stockMinimo;

	/**
	 * stock máximo del producto.
	 */
	@Column
	private Integer stockMaximo;
	
	/**
	 * precio de venta
	 */
	@Column
	@NotNull(message="{producto.precio.required}")
	private Float precio;
	
	/**
	 * iva
	 */
	@Column
	@NotNull(message="{producto.iva.required}")
	private Float iva;


	/**
	 * categoría del producto
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="categoria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{producto.categoria.required}")
	private CategoriaProducto categoria;

	
	/**
	 * estado del producto
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{producto.estado.required}")
	private EstadoProducto estadoProducto;

	
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
	 * @return the categoria
	 */
	public CategoriaProducto getCategoria() {
		return categoria;
	}


	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the precio
	 */
	public Float getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Float precio) {
		this.precio = precio;
	}


	/**
	 * @return the iva
	 */
	public Float getIva() {
		return iva;
	}


	/**
	 * @param iva the iva to set
	 */
	public void setIva(Float iva) {
		this.iva = iva;
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


	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}


	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	@Override
	public String toString(){
		return getNombre();
	}


	/**
	 * @return the stockActual
	 */
	public Integer getStockActual() {
		return stockActual;
	}


	/**
	 * @param stockActual the stockActual to set
	 */
	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}


	/**
	 * @return the stockMinimo
	 */
	public Integer getStockMinimo() {
		return stockMinimo;
	}


	/**
	 * @param stockMinimo the stockMinimo to set
	 */
	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}


	/**
	 * @return the stockMaximo
	 */
	public Integer getStockMaximo() {
		return stockMaximo;
	}


	/**
	 * @param stockMaximo the stockMaximo to set
	 */
	public void setStockMaximo(Integer stockMaximo) {
		this.stockMaximo = stockMaximo;
	}


	/**
	 * @return the estadoProducto
	 */
	public EstadoProducto getEstadoProducto() {
		return estadoProducto;
	}


	/**
	 * @param estadoProducto the estadoProducto to set
	 */
	public void setEstadoProducto(EstadoProducto estadoProducto) {
		this.estadoProducto = estadoProducto;
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

	public Float getPrecioConIva() {
		return precio + (precio*iva/100);
	}
}
