package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Detalle de pago de venta.
 * 
 * Representa lo que se le paga a una venta con un pago.
 * 
 * Esto es porque un pago puede cancelar varias ventas, entonces
 * si el cliente paga 400$ para cancelar 2 ventas impagas, con el detalle podemos 
 * saber cómo se distribuyen esos $400,  por ejemplo:
 *    - $150 venta 1
 *    - $250 venta 2
 *    
 *    
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_pagoventa")
public class DetallePagoVenta extends DetallePago{

	

	public DetallePagoVenta(){
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