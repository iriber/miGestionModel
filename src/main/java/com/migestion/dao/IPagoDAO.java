package com.migestion.dao;

import com.migestion.model.EstadisticaPago;
import com.migestion.services.criteria.PagoCriteria;


/**
 * DAO para pagos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 28/10/2013
 *
 */
public interface IPagoDAO<TCriteria extends PagoCriteria> {

	/**
	 * estadísticas de pago dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaPago getEstadisticaPago(TCriteria criteria);

}
