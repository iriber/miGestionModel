package com.migestion.model;

import java.io.Serializable;


/**
 * Estadística de caja
 * 
 * @author Bernardo Iribarne
 * @since 30/10/2013
 */
public class EstadisticaCaja implements Serializable{

	private Float importeTotalHaber;
	
	private Float importeTotalDebe;
	
	private Float saldoInicial=0F;

	private Integer cantidad;

	public Float getSaldo() {
		
		Float debe = (getImporteTotalDebe()!=null)?getImporteTotalDebe():0F;
		Float haber = (getImporteTotalHaber()!=null)?getImporteTotalHaber():0F;
		
		Float saldo = haber - debe;

		
		return saldo; // + saldoInicial;
	}


	/**
	 * @return the importeTotalHaber
	 */
	public Float getImporteTotalHaber() {
		return importeTotalHaber;
	}

	/**
	 * @param importeTotalHaber the importeTotalHaber to set
	 */
	public void setImporteTotalHaber(Float importeTotalHaber) {
		this.importeTotalHaber = importeTotalHaber;
	}

	/**
	 * @return the importeTotalDebe
	 */
	public Float getImporteTotalDebe() {
		return importeTotalDebe;
	}

	/**
	 * @param importeTotalDebe the importeTotalDebe to set
	 */
	public void setImporteTotalDebe(Float importeTotalDebe) {
		this.importeTotalDebe = importeTotalDebe;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	/**
	 * @return the saldoInicial
	 */
	public Float getSaldoInicial() {
		return saldoInicial;
	}


	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(Float saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
}