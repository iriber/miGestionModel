package com.migestion.services;

import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.services.criteria.MovimientoCuentaProveedorCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de movimientos de cuentas
 * corrientes de proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public interface IMovimientoCuentaProveedorService extends IGenericService<MovimientoCuentaProveedor, MovimientoCuentaProveedorCriteria>{
 
	/**
	 * retorna balance del proveedor
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoCuentaProveedorCriteria criteria) throws ServiceException;;


}
