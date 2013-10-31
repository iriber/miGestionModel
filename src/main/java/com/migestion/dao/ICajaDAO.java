package com.migestion.dao;

import com.migestion.model.EstadisticaCaja;
import com.migestion.services.criteria.CajaCriteria;


/**
 * DAO para cajas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public interface ICajaDAO {

	/**
	 * estadísticas de caja dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaCaja getEstadisticaCaja(CajaCriteria criteria);

}
