package com.migestion.services;

import com.migestion.model.Balance;
import com.migestion.model.MovimientoNotaCredito;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public interface IMovimientoNotaCreditoService extends IGenericService<MovimientoNotaCredito, MovimientoNotaCreditoCriteria>{
 
	/**
	 * retorna balance de notas de crédito
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoNotaCreditoCriteria criteria) throws ServiceException;;


}
