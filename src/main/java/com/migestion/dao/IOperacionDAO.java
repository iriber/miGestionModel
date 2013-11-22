package com.migestion.dao;

import com.migestion.model.EstadisticaOperacion;
import com.migestion.services.criteria.OperacionCriteria;


/**
 * DAO para ventas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 23/10/2013
 *
 */
public interface IOperacionDAO {

	/**
	 * estadísticas de operación dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaOperacion getEstadisticaOperacion(OperacionCriteria criteria);

}
