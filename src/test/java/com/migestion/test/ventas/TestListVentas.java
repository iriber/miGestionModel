package com.migestion.test.ventas;

import java.util.List;

import com.migestion.model.Venta;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar ventas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 */
public class TestListVentas extends TestListEntities<Venta>{

	@Override
	protected List<Venta> list() throws ServiceException {
		
		VentaCriteria criteria = new VentaCriteria();
		return ServiceFactory.getVentaService().list(criteria);
		
	}

}
