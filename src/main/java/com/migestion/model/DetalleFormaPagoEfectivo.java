package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Detalle de un pago en efectivo.
 * 
 * Representa el monto de un pago realizado en efectivo.
 * 
 * Está asociado a un movimiento de caja.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_formapago_efectivo")
public class DetalleFormaPagoEfectivo extends DetalleFormaPago{

	/**
	 * caja donde se realiza el pago
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="caja_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoEfectivo.caja.required}")
	private Caja caja;
	
	
	public DetalleFormaPagoEfectivo(){
		super();
	}

	@Override
	public MovimientoCuenta createMovimiento() {
		return new MovimientoCaja();
	}
	
	
	@Override
	public FormaPago getFormaPago() {
		return FormaPago.EFECTIVO;
	}

	/**
	 * @return the caja
	 */
	public Caja getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(Caja caja) {
		this.caja = caja;
		
		((MovimientoCaja)getMovimiento()).setCaja(caja);
		
	}
	
}