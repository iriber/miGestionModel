package com.migestion.services;

import com.migestion.model.Caja;
import com.migestion.model.EstadisticaCaja;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de cajas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public interface ICajaService extends IGenericService<Caja, CajaCriteria>{
 
	/**
	 * retorna los totales de caja
	 * @param criteria
	 * @return
	 */
	public EstadisticaCaja getEstadisticaCaja(CajaCriteria criteria) throws ServiceException;;
	

	/**
	 * se cierra una caja.
	 * @param caja
	 */
	public void cerrarCaja(Long oid) throws ServiceException;
}
