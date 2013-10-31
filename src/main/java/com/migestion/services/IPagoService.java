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
public interface IPagoService extends IGenericService<Pago, PagoCriteria>{
 
	/**
	 * retorna los totales de pagos
	 * @param criteria
	 * @return
	 */
	public EstadisticaPago getEstadisticaPago(PagoCriteria criteria) throws ServiceException;;
	

	/**
	 * se anula el pago.
	 * @param pago
	 */
	public Pago anularPago(Long oid) throws ServiceException;

}
