package com.migestion.test.pagos;

import java.util.List;

import com.migestion.model.PagoProveedor;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoProveedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar pagos a proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/12/2013
 */
public class TestListPagosProveedor extends TestListEntities<PagoProveedor>{

	@Override
	protected List<PagoProveedor> list() throws ServiceException {
		
		PagoProveedorCriteria criteria = new PagoProveedorCriteria();
		criteria.setOperacion( ServiceFactory.getVentaService().get(32768L) );
		return ServiceFactory.getPagoProveedorService().list(criteria);
		
	}

}
