package com.migestion.test.proveedores;

import java.util.Date;
import java.util.List;

import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoCuentaProveedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar movimientos de proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 */
public class TestListMovimientosProveedor extends TestListEntities<MovimientoCuentaProveedor>{

	@Override
	protected List<MovimientoCuentaProveedor> list() throws ServiceException {
		
		MovimientoCuentaProveedorCriteria criteria = new MovimientoCuentaProveedorCriteria();
		criteria.setFecha( new Date() );
		criteria.setProveedor( ServiceFactory.getProveedorService().get(1L));
		return ServiceFactory.getMovimientoCuentaProveedorService().list(criteria);
		
	}

}
