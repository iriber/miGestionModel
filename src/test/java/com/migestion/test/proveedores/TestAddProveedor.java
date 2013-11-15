package com.migestion.test.proveedores;

import com.migestion.model.CondicionIVA;
import com.migestion.model.Proveedor;
import com.migestion.model.TipoDocumento;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ProveedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar proveedores
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public class TestAddProveedor extends TestAddEntity<Proveedor, ProveedorCriteria>{


	@Override
	protected Proveedor getEntity() throws ServiceException {
		Proveedor c = new Proveedor();
		c.setNombre("Proveedor 1");
		c.setTipoDocumento(TipoDocumento.DNI);
		c.setCondicionIVA(CondicionIVA.CONSUMIDOR_FINAL);
		return c;
	}

	@Override
	protected ProveedorCriteria getCriteria() {
		return new ProveedorCriteria();
	}

	@Override
	protected IGenericService<Proveedor, ProveedorCriteria> getService() {
		return ServiceFactory.getProveedorService();
	}

}
