package com.migestion.dao;

import com.migestion.model.EstadisticaCaja;
import com.migestion.services.criteria.MovimientoCajaCriteria;


/**
 * DAO para movimientos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public interface IMovimientoCajaDAO {

	/**
	 * estadísticas de caja dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaCaja getEstadisticaCaja(MovimientoCajaCriteria criteria);

}
