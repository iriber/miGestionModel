package com.migestion.services.criteria;

import java.util.Date;

/**
 * Criterio de búsqueda para movimientos de cuenta.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoCuentaCriteria extends Criteria{

	private Date fecha;
	
	private Date fechaDesde;
	
	private Date fechaHasta;
	
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void orderByFechaHora(String type){
		
		this.addOrder("fechaHora", type);
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
}