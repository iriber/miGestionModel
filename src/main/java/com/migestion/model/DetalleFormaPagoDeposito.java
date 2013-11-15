package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Detalle de un pago por depósito en banco.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_formapago_deposito")
public class DetalleFormaPagoDeposito extends DetalleFormaPago{

	/**
	 * nro de comprobante de la transacción
	 */
	@Column
	private String nroComprobante;
	
	/**
	 * cuenta bancaria en la cual se deposita
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cuenta_bancaria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoDeposito.cuentaBancaria.required}")
	private CuentaBancaria cuentaBancaria;

	
	public DetalleFormaPagoDeposito(){
		super();
	}


	/**
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}


	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}


	/**
	 * @return the cuentaBancaria
	 */
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}


	/**
	 * @param cuentaBancaria the cuentaBancaria to set
	 */
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
		
		((MovimientoCuentaBancaria)getMovimiento()).setCuentaBancaria(cuentaBancaria);
        
	}

	@Override
	public FormaPago getFormaPago() {
		return FormaPago.DEPOSITO;
	}


	@Override
	public MovimientoCuenta createMovimiento() {
		return new MovimientoCuentaBancaria();
	}

}