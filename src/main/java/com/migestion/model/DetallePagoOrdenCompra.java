package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Detalle de pago de una orden de compra.
 * 
 * Representa lo que se le paga sobre una orden de compra (pago a proveedor).
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */

@Entity
@Table(name="detalle_pago_ordencompra")
public class DetallePagoOrdenCompra extends DetallePago{

	

	public DetallePagoOrdenCompra(){
		super();
	}

	/**
	 * @return the OrdenCompra
	 */
	public OrdenCompra getOrdenCompra() {
		return (OrdenCompra)getOperacion();
	}


	/**
	 * @param venta the venta to set
	 */
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		setOperacion(ordenCompra);
	}
}