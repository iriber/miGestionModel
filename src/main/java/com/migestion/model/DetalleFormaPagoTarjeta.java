package com.migestion.model;


import java.util.Date;

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
 * Detalle de un pago con tarjeta.
 * 
 * Representa el monto de un pago realizado con tarjeta.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_formapago_tarjeta")
public class DetalleFormaPagoTarjeta extends DetalleFormaPago{

	/**
	 * banco emisor
	 */
	@Column
	@NotNull
	private String tarjeta;

	/**
	 * número de tarjeta 
	 */
	@Column
	@NotNull
	private String numero;

	/**
	 * titular
	 */
	@Column
	@NotNull
	private String titular;
	
	/**
	 * fecha de vencimiento
	 */
	@Column
	private Date fechaVencimiento;
	
	
	/**
	 * banco emisor
	 */
	@Column
	private String banco;

	/**
	 * cuenta bancaria en la cual se recibe
	 * el pago de la tarjeta
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cuenta_bancaria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoTarjeta.cuentaBancaria.required}")
	private CuentaBancaria destino;
	
	
	public DetalleFormaPagoTarjeta(){
		
	}



	/**
	 * @return the tarjeta
	 */
	public String getTarjeta() {
		return tarjeta;
	}



	/**
	 * @param tarjeta the tarjeta to set
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}



	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}



	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}



	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}



	/**
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}



	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}



	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}



	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}



	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	@Override
	public FormaPago getFormaPago() {
		return FormaPago.TARJETA;
	}



	@Override
	public MovimientoCuenta createMovimiento() {
		return new MovimientoCuentaBancaria();
	}
	
	/**
	 * @return the destino
	 */
	public CuentaBancaria getDestino() {
		return destino;
	}


	/**
	 * @param destino the destino to set
	 */
	public void setDestino(CuentaBancaria destino) {
		this.destino = destino;
		
		((MovimientoCuentaBancaria)getMovimiento()).setCuentaBancaria(destino);
        
	}


}