package com.migestion.model;


import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Detalle de una operación.
 * Detalla a un producto de la operación
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class DetalleOperacion{

	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)//, generator = "SEQ_DETALLEPAGO")
	//@SequenceGenerator(name = "SEQ_DETALLEPAGO", sequenceName = "SEQ_DETALLEPAGO", allocationSize = 1)
	private Long oid;

	/**
	 * producto
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="producto_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleOperacion.producto.required}")
	private Producto producto;
	
	/**
	 * cantidad asociada al producto
	 */
	@Column
	@NotNull(message="{detalleOperacion.cantidad.required}")
	private Integer cantidad;
	
	/**
	 * precio unitario del producto
	 */
	@Column
	@NotNull(message="{detalleOperacion.precioUnitario.required}")
	private Float precioUnitario;
	
	/**
	 * descuento aplicado sobre (preciounitario*cantidad)
	 */
	@Column
	private Float descuento;

	/**
	 * operación
	 */
	@ManyToOne
	@JoinColumn(name="operacion_oid", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)	
	private Operacion operacion;
	
	public DetalleOperacion(){
		this.cantidad = 0;
		this.descuento = 0F;
		this.precioUnitario = 0F;
	}
		
	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the precioUnitario
	 */
	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the descuento
	 */
	public Float getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}
	
	/**
	 * subtotal del detalle
	 * @return
	 */
	public Float getSubtotal(){
		Float subtotal =  (getPrecioUnitario() * getCantidad() );
		
		if( getDescuento()!=null)
			subtotal -= getDescuento();
		
		return subtotal;
	}

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
	 * @return the operacion
	 */
	public Operacion getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

}