package com.migestion.test.clientes;

import java.util.List;

import com.migestion.model.Cliente;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 */
public class TestListClientes extends TestListEntities<Cliente>{

	@Override
	protected List<Cliente> list() throws ServiceException {
		
		ClienteCriteria criteria = new ClienteCriteria();
		return ServiceFactory.getClienteService().list(criteria);
		
	}

}
