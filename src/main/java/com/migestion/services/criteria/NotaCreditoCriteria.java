package com.migestion.services.criteria;

import java.util.Date;

import com.migestion.model.Cliente;

/**
 * Criterio de búsqueda para notas de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class NotaCreditoCriteria extends Criteria{

	/**
	 * numero
	 */
	private String numero;
	
	private Long oid;

	private Date fecha;
	
	private Cliente cliente;
	
	public void orderByFecha(String type){
		
		this.addOrder("fecha", type);
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Long getOid() {
		return oid;
	}


	public void setOid(Long oid) {
		this.oid = oid;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}