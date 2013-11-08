package com.migestion.services.criteria;

import java.util.Date;

import com.migestion.model.Caja;
import com.migestion.model.Sucursal;

/**
 * Criterio de búsqueda para movimientos de cuenta.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoCuentaCriteria extends Criteria{

	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void orderByFechaHora(String type){
		
		this.addOrder("fechaHora", type);
	}
	
	
}