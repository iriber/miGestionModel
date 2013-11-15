package com.migestion.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Proveedor
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */

@Entity
@Table(name="proveedor")
public class Proveedor extends Persona{

	/**
	 * cuit
	 */
	@Column
	private String cuit;
	
	/**
	 * contacto
	 */
	@Column
	private String contacto;
	
	/**
	 * condición iva
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{proveedor.condicionIVA.required}")
	private CondicionIVA condicionIVA;
	

	/**
	 * domicilio de facturación
	 */
	@Column
	private String domicilioFacturacion;
	
	/**
	 * saldo de la cuenta corriente del proveedor.
	 */
	@Column
	private Float saldo;
	
	/**
	 * saldo al descubierto sobre la cuenta corriente
	 */
	@Column
	private Float descubierto;

	/**
	 * para determinar si tiene o no cta cte.
	 */
	@Column
	private Boolean tieneCtaCte;
	
	public Proveedor(){
		this.tieneCtaCte = true;
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
	 * @return the contacto
	 */
	public String getContacto() {
		return contacto;
	}


	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	/**
	 * @return the condicionIVA
	 */
	public CondicionIVA getCondicionIVA() {
		return condicionIVA;
	}


	/**
	 * @param condicionIVA the condicionIVA to set
	 */
	public void setCondicionIVA(CondicionIVA condicionIVA) {
		this.condicionIVA = condicionIVA;
	}


	/**
	 * @return the domicilioFacturacion
	 */
	public String getDomicilioFacturacion() {
		return domicilioFacturacion;
	}


	/**
	 * @param domicilioFacturacion the domicilioFacturacion to set
	 */
	public void setDomicilioFacturacion(String domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}


	public Float getSaldo() {
		return saldo;
	}


	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}


	public Float getDescubierto() {
		return descubierto;
	}


	public void setDescubierto(Float descubierto) {
		this.descubierto = descubierto;
	}


	public Boolean getTieneCtaCte() {
		return tieneCtaCte;
	}


	public void setTieneCtaCte(Boolean tieneCtaCte) {
		this.tieneCtaCte = tieneCtaCte;
	}

}