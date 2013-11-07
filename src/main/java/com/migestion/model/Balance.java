package com.migestion.model;

import java.io.Serializable;


/**
 * Balance de una cuenta
 * 
 * @author Bernardo Iribarne
 * @since 07/11/2013
 */
public class Balance implements Serializable{


	private String descripcion;

	private Float haber=0F;
	
	private Float debe=0F;
	
	private Float saldoInicial=0F;

	private Integer cantidadMovimientos=0;

	public Float getSaldo() {
		
		Float debe = (getDebe()!=null)?getDebe():0F;
		Float haber = (getHaber()!=null)?getHaber():0F;
		
		Float saldo = haber - debe;
		
		return saldo + saldoInicial;
	}



	public Float getHaber() {
		return haber;
	}



	public void setHaber(Float haber) {
		this.haber = haber;
	}



	public Float getDebe() {
		return debe;
	}



	public void setDebe(Float debe) {
		this.debe = debe;
	}



	public Integer getCantidadMovimientos() {
		return cantidadMovimientos;
	}



	public void setCantidadMovimientos(Integer cantidadMovimientos) {
		this.cantidadMovimientos = cantidadMovimientos;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}