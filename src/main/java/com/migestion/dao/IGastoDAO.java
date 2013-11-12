package com.migestion.dao;

import com.migestion.model.EstadisticaGasto;
import com.migestion.services.criteria.GastoCriteria;


/**
 * DAO para gastos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public interface IGastoDAO {

	/**
	 * estadísticas de gastos dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaGasto getEstadisticaGasto(GastoCriteria criteria);

}
