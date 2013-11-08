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
 * Detalle de un pago con nota de crédito.
 * 
 * Representa el monto de un pago realizado con una nota de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */

@Entity
@Table(name="detalle_formapago_nota_credito")
public class DetalleFormaPagoNotaCredito extends DetalleFormaPago{

	/**
	 * nota de crédito
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="nota_credito_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoNotaCredito.notaCredito.required}")
	private NotaCredito notaCredito;

	
	public DetalleFormaPagoNotaCredito(){
		
	}

	@Override
	public FormaPago getFormaPago() {
		return FormaPago.NOTA_CREDITO;
	}

	public String toString(){
		return this.getNotaCredito().toString();
	}
	
	public String toString(I18nLocale i18n){
		return i18n.message( getFormaPago().getNombre() ) + " " + getNotaCredito().toString(i18n);
	}


	@Override
	public MovimientoCuenta createMovimiento() {
		
		return new MovimientoNotaCredito();
	}



	public NotaCredito getNotaCredito() {
		return notaCredito;
	}



	public void setNotaCredito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
		this.setMonto( notaCredito.getMonto() );
	}
}