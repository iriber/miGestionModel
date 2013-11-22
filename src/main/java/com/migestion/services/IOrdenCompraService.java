package com.migestion.services;

import com.migestion.model.EstadisticaOperacion;
import com.migestion.model.OrdenCompra;
import com.migestion.services.criteria.OperacionCriteria;
import com.migestion.services.criteria.OrdenCompraCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de órdenes de compra
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */
public interface IOrdenCompraService extends IGenericService<OrdenCompra, OrdenCompraCriteria>{
 
	/**
	 * retorna los totales de órdenes de compra
	 * @param criteria
	 * @return
	 */
	public EstadisticaOperacion getEstadistica(OperacionCriteria criteria) throws ServiceException;
	

	/**
	 * se anula la orden de compra.
	 * @param oid
	 */
	public void anularOrdenCompra(Long oid) throws ServiceException;
}
