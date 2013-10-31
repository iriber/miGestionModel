package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.migestion.i18n.I18nLocale;

/**
 * Detalle de un pago con cheque.
 * 
 * Representa el monto de un pago realizado con cheque.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_formapago_cheque")
public class DetalleFormaPagoCheque extends DetalleFormaPago{

	/**
	 * cheque
	 */
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="cheque_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoCeque.cheque.required}")
	private Cheque cheque;

	
	//TODO chequear que movimiento y dónde lo genera.
	
	public DetalleFormaPagoCheque(){
		
	}


	/**
	 * @return the cheque
	 */
	public Cheque getCheque() {
		return cheque;
	}


	/**
	 * @param cheque the cheque to set
	 */
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
		
		this.setMonto( cheque.getMonto() );
		
	}


	@Override
	public FormaPago getFormaPago() {
		return FormaPago.CHEQUE;
	}

	public String toString(){
		return this.getCheque().toString();
	}
	
	public String toString(I18nLocale i18n){
		return i18n.message( getFormaPago().getNombre() ) + " " + getCheque().toString(i18n);
	}


	@Override
	public MovimientoCuenta createMovimiento() {
		
		return new MovimientoCheque();
	}
}