package com.migestion.test.sucursales;

import java.util.List;

import com.migestion.model.Sucursal;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.SucursalCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar sucursales
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */
public class TestListSucursales extends TestListEntities<Sucursal>{

	@Override
	protected List<Sucursal> list() throws ServiceException {
		
		SucursalCriteria criteria = new SucursalCriteria();
		return ServiceFactory.getSucursalService().list(criteria);
		
	}

}
