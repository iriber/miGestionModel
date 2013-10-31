package com.migestion.test.vendedores;

import com.migestion.model.TipoDocumento;
import com.migestion.model.Vendedor;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar vendedores
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 *
 */
public class TestAddVendedor extends TestAddEntity<Vendedor, VendedorCriteria>{


	@Override
	protected Vendedor getEntity() throws ServiceException {
		Vendedor c = new Vendedor();
		c.setNombre("TITULAR DEL COMERCIO");
		c.setTipoDocumento(TipoDocumento.DNI);
		return c;
	}

	@Override
	protected VendedorCriteria getCriteria() {
		return new VendedorCriteria();
	}

	@Override
	protected IGenericService<Vendedor, VendedorCriteria> getService() {
		return ServiceFactory.getVendedorService();
	}

}
