package com.migestion.test.ordenesCompra;

import java.util.List;

import com.migestion.model.OrdenCompra;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.OrdenCompraCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar órdenes de compra
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 */
public class TestListOrdenesCompra extends TestListEntities<OrdenCompra>{

	@Override
	protected List<OrdenCompra> list() throws ServiceException {
		
		OrdenCompraCriteria criteria = new OrdenCompraCriteria();
		return ServiceFactory.getOrdenCompraService().list(criteria);
		
	}

}
