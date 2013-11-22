package com.migestion.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Detalle de una orden de compra
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */

@Entity
@Table(name="detalle_orden_compra")
public class DetalleOrdenCompra extends DetalleOperacion{

	/**
	 * cantidad entregada del producto
	 */
	@Column
	private Integer cantidadEntregada;
	
	public DetalleOrdenCompra(){

		super();
	}


	/**
	 * @return the OrdenCompra
	 */
	public OrdenCompra getOrdenCompra() {
		return (OrdenCompra)getOperacion();
	}


	/**
	 * @param ordenCompra the OrdenCompra to set
	 */
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		setOperacion(ordenCompra);
	}


	public Integer getCantidadEntregada() {
		return cantidadEntregada;
	}


	public void setCantidadEntregada(Integer cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

}