package com.migestion.services.criteria;

import java.util.Date;

import com.migestion.model.Cliente;
import com.migestion.model.Vendedor;

/**
 * Criterio de búsqueda para operaciones.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
public class OperacionCriteria extends Criteria{

	/**
	 * fecha de venta desde
	 */
	private Date fechaDesde;

	/**
	 * fecha de venta hasta
	 */
	private Date fechaHasta;

	/**
	 * cliente
	 */
	private Cliente cliente;
	
	/**
	 * vendedor
	 */
	private Vendedor vendedor;


	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public void orderByFecha(String type){
		
		this.addOrder("fecha", type);
	}

	public void orderByCodigo(String type){
		
		this.addOrder("oid", type);
	}
}