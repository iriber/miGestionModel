package com.migestion.services;

import com.migestion.model.Vendedor;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de vendedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 *
 */
public interface IVendedorService extends IGenericService<Vendedor, VendedorCriteria>{
 
	public Vendedor getTitularComercio() throws ServiceException;
}
