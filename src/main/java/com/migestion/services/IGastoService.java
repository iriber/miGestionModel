package com.migestion.services;

import com.migestion.model.EstadisticaGasto;
import com.migestion.model.Gasto;
import com.migestion.services.criteria.GastoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de gastos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public interface IGastoService extends IGenericService<Gasto, GastoCriteria>{
 
	/**
	 * retorna los totales de gastos
	 * @param criteria
	 * @return
	 */
	public EstadisticaGasto getEstadisticaGasto(GastoCriteria criteria) throws ServiceException;;
	
}
