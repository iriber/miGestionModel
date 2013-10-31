package com.migestion.test.vendedores;

import java.util.List;

import com.migestion.model.Vendedor;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar vendedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 */
public class TestListVendedores extends TestListEntities<Vendedor>{

	@Override
	protected List<Vendedor> list() throws ServiceException {
		
		VendedorCriteria criteria = new VendedorCriteria();
		return ServiceFactory.getVendedorService().list(criteria);
		
	}

}
