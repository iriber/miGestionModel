package com.migestion.services.criteria;

import java.util.Date;

/**
 * Criterio de búsqueda para cheques.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class ChequeCriteria extends Criteria{

	/**
	 * numero
	 */
	private String numero;
	
	private String banco;

	private Date fechaVencimiento;
	
	
	public void orderByNumero(String type){
		
		this.addOrder("numero", type);
	}

	public void orderByFechaVencimiento(String type){
		
		this.addOrder("fechaVencimiento", type);
	}

	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


}