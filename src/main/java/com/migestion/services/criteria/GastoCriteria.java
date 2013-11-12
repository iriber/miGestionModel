package com.migestion.services.criteria;

import java.util.Date;

/**
 * Criterio de búsqueda para gastos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public class GastoCriteria extends Criteria{

	/**
	 * fecha de gasto desde
	 */
	private Date fechaDesde;

	/**
	 * fecha de gasto hasta
	 */
	private Date fechaHasta;

	
	public GastoCriteria(){
		
	}

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


	public void orderByFecha(String type){
		
		this.addOrder("fecha", type);
	}

	public void orderByCodigo(String type){
		
		this.addOrder("oid", type);
	}
	
}