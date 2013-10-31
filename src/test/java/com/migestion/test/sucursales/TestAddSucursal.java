package com.migestion.test.sucursales;

import com.migestion.model.Sucursal;
import com.migestion.model.TipoDocumento;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.SucursalCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar sucursales
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class TestAddSucursal extends TestAddEntity<Sucursal, SucursalCriteria>{


	@Override
	protected Sucursal getEntity() throws ServiceException {
		Sucursal c = new Sucursal();
		c.setNombre("CASA MATRIZ");
		return c;
	}

	@Override
	protected SucursalCriteria getCriteria() {
		return new SucursalCriteria();
	}

	@Override
	protected IGenericService<Sucursal, SucursalCriteria> getService() {
		return ServiceFactory.getSucursalService();
	}

}
