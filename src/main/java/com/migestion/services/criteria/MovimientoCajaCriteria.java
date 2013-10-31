package com.migestion.services.criteria;

import java.util.Date;

import com.migestion.model.Caja;

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