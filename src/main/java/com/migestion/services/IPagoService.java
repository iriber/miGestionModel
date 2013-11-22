package com.migestion.services;

import com.migestion.model.EstadisticaPago;
import com.migestion.model.Pago;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de pagos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public interface IPagoService<T extends Pago, TCriteria extends PagoCriteria> extends IGenericService<T, TCriteria>{
 
	/**
	 * retorna los totales de pagos
	 * @param criteria
	 * @return
	 */
	public EstadisticaPago getEstadisticaPago(TCriteria criteria) throws ServiceException;;
	

	/**
	 * se anula el pago.
	 * @param pago
	 */
	public Pago anularPago(Long oid) throws ServiceException;

}
