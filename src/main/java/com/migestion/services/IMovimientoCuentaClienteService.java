package com.migestion.services;

import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.services.criteria.MovimientoCuentaClienteCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de cuentas
 * corrientes de clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
public interface IMovimientoCuentaClienteService extends IGenericService<MovimientoCuentaCliente, MovimientoCuentaClienteCriteria>{
 
	/**
	 * retorna balance del cliente
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoCuentaClienteCriteria criteria) throws ServiceException;;


}
