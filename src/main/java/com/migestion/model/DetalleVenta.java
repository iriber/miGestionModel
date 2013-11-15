package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Detalle de venta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */

@Entity
@Table(name="detalle_venta")
public class DetalleVenta extends DetalleOperacion{

	
	
	public DetalleVenta(){

		super();
	}


	/**
	 * @return the venta
	 */
	public Venta getVenta() {
		return (Venta)getOperacion();
	}


	/**
	 * @param venta the venta to set
	 */
	public void setVenta(Venta venta) {
		setOperacion(venta);
	}

}