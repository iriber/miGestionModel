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
 * Detalle de un gasto con cheque.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */

@Entity
@Table(name="gasto_cheque")
public class GastoCheque extends Gasto{

	/**
	 * cheque
	 */
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cheque_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{gastoCeque.cheque.required}")
	private Cheque cheque;

	
	public GastoCheque(){
		
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
		MovimientoCheque movimiento = new MovimientoCheque();
		movimiento.setCheque(getCheque());
		return movimiento;
	}
	
	
}