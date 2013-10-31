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
 * Detalle de un pago por transferencia bancaria.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="detalle_formapago_transferencia")
public class DetalleFormaPagoTransferencia extends DetalleFormaPago{

	/**
	 * nro de comprobante de la transacción
	 */
	@Column
	private String nroComprobante;
	
	/**
	 * cuenta bancaria en la cual se recibe
	 * la transferencia
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cuenta_bancaria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{detalleFormaPagoTransferencia.cuentaBancaria.required}")
	private CuentaBancaria destino;

	/**
	 * banco desde donde se realiza la transferencia (origen)
	 */
	@Column
	private String banco;
	
	/**
	 * titular del banco origen
	 */
	@Column
	private String titular;

	/**
	 * cbu del banco origen
	 */
	@Column
	private String cbu;
	

	/**
	 * cuit del titular del banco origen
	 */
	@Column
	private String cuit;
	

	/**
	 * númnero de cuenta del banco origen
	 */
	@Column
	private String nroCuenta;
	
	
	public DetalleFormaPagoTransferencia(){
		
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
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}


	/**
	 * @param cbu the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}


	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}


	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	/**
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}


	/**
	 * @param nroCuenta the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	@Override
	public FormaPago getFormaPago() {
		return FormaPago.TRANSFERENCIA;
	}
	

	@Override
	public MovimientoCuenta createMovimiento() {
		return new MovimientoCuentaBancaria();
	}


}