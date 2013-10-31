package com.migestion.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa una cuenta bancaria.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */

@Entity
@Table(name="cuenta_bancaria")
public class CuentaBancaria extends GenericEntity{

	/**
	 * nombre del banco
	 */
	@Column
	private String nombre;
	
	/**
	 * número de cuenta 
	 */
	@Column
	private String nroCuenta;
	
	/**
	 * titular de la cuenta
	 */
	@Column
	private String titular;
	
	/**
	 * cuit del titular de la cuenta
	 */
	@Column
	private String cuit;
		
	/**
	 * cbu
	 */
	@Column
	private String cbu;
		
	
	/**
	 * sucursal
	 */
	@Column
	private String sucursal;
	
	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	

	/**
	 * identificador de la entity
	 */
	@Id @GeneratedValue
	@Column
	private Long oid;

	
	/**
	 * saldo inicial 
	 */
	@Column
	private Float saldoInicial;
	
	/**
	 * saldo
	 */
	@Column
	private Float saldo;


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

	
	public CuentaBancaria(){
		
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;

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


	/**
	 * @return the saldo
	 */
	public Float getSaldo() {
		return saldo;
	}


	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public String toString(){
		
		return getNombre() + " - " + getNroCuenta() + " - " + getTitular()  + " - " + getSaldo(); 
		
	}
}