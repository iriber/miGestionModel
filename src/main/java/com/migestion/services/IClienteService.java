package com.migestion.services;

import com.migestion.model.Cliente;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public interface IClienteService extends IGenericService<Cliente, ClienteCriteria>{

	public Cliente getClienteMostrador() throws ServiceException;
	
}
