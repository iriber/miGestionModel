package com.migestion.services;

import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de cuentas bancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public interface IMovimientoCuentaBancariaService extends IGenericService<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria>{
 
	/**
	 * retorna balance de cuenta bancaria
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoCuentaBancariaCriteria criteria) throws ServiceException;;


	
}
