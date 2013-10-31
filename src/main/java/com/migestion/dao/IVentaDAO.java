package com.migestion.dao;

import com.migestion.model.EstadisticaVenta;
import com.migestion.services.criteria.VentaCriteria;


/**
 * DAO para ventas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 23/10/2013
 *
 */
public interface IVentaDAO {

	/**
	 * estadísticas de venta dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public EstadisticaVenta getEstadisticaVenta(VentaCriteria criteria);

}
