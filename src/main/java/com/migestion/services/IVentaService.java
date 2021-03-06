package com.migestion.services;

import com.migestion.model.EstadisticaOperacion;
import com.migestion.model.Venta;
import com.migestion.services.criteria.OperacionCriteria;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de ventas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
public interface IVentaService extends IGenericService<Venta, VentaCriteria>{
 
	/**
	 * retorna los totales de ventas
	 * @param criteria
	 * @return
	 */
	public EstadisticaOperacion getEstadistica(OperacionCriteria criteria) throws ServiceException;
	

	/**
	 * se anula la venta.
	 * @param venta
	 */
	public void anularVenta(Long oid) throws ServiceException;
}
