package com.migestion.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.migestion.i18n.I18nLocale;

/**
 * Representa un cheque.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="cheque")
public class Cheque extends GenericEntity{

	/**
	 * número de cheque 
	 */
	@Column
	private String numero;
	
	/**
	 * banco emisor
	 */
	@Column
	private String banco;

	/**
	 * monto sobre la operación
	 */
	@Column
	@NotNull(message="{detallePago.monto.required}")
	private Float monto;

	/**
	 * fecha de vencimiento
	 */
	@Column
	private Date fechaVencimiento;

	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	
	//TODO tipo de cheque?
	

	/**
	 * identificador de la entity
	 */
	@Id @GeneratedValue
	@Column
	private Long oid;


	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}


	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Cheque(){
		
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
	 * @return the monto
	 */
	public Float getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Float monto) {
		this.monto = monto;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String toString(){
		return "Banco: " + getBanco() + " Nro: " + getNumero() + " F.Vencimiento: " + new SimpleDateFormat("dd/MM/yyyy").format( getFechaVencimiento() );
	}
	
	public String toString(I18nLocale i18n){
		return i18n.message("cheque.banco") + ": " + getBanco() + " " + i18n.message("cheque.numero") + ": " + getNumero() + " " + i18n.message("cheque.fechaVencimiento") + ": " +  new SimpleDateFormat("dd/MM/yyyy").format( getFechaVencimiento() );
	}

}