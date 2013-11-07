package com.migestion.services.criteria;

import java.util.Date;

import com.migestion.model.Caja;
import com.migestion.model.Sucursal;

/**
 * Criterio de búsqueda para movimientos de caja.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class MovimientoCajaCriteria extends Criteria{

	/**
	 * caja
	 */
	private Caja caja;

	
	private Date fecha;
	
	private Sucursal sucursal;
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the caja
	 */
	public Caja getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public void orderByFechaHora(String type){
		
		this.addOrder("fechaHora", type);
	}
	
	
}