package com.migestion.services;

import com.migestion.model.Balance;
import com.migestion.model.MovimientoCheque;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public interface IMovimientoChequeService extends IGenericService<MovimientoCheque, MovimientoChequeCriteria>{
 
	/**
	 * retorna balance de cheques
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoChequeCriteria criteria) throws ServiceException;;


}
