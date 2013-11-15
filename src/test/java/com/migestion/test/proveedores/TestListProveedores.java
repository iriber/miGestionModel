package com.migestion.test.proveedores;

import java.util.List;

import com.migestion.model.Proveedor;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ProveedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 */
public class TestListProveedores extends TestListEntities<Proveedor>{

	@Override
	protected List<Proveedor> list() throws ServiceException {
		
		ProveedorCriteria criteria = new ProveedorCriteria();
		return ServiceFactory.getProveedorService().list(criteria);
		
	}

}
