package com.migestion.services;

import com.migestion.model.EstadisticaCaja;
import com.migestion.model.MovimientoCaja;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public interface IMovimientoCajaService extends IGenericService<MovimientoCaja, MovimientoCajaCriteria>{
 
	/**
	 * retorna los totales de caja
	 * @param criteria
	 * @return
	 */
	public EstadisticaCaja getEstadisticaCaja(MovimientoCajaCriteria criteria) throws ServiceException;;


}
