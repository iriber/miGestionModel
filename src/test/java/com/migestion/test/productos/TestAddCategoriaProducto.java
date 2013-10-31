package com.migestion.test.productos;

import com.migestion.model.CategoriaProducto;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CategoriaProductoCriteria;
import com.migestion.test.TestAddEntity;

/**
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class TestAddCategoriaProducto extends TestAddEntity<CategoriaProducto, CategoriaProductoCriteria>{


	@Override
	protected CategoriaProducto getEntity() {
		CategoriaProducto cp = new CategoriaProducto();
		cp.setNombre("RUBRO GENERAL");
		return cp;
	}

	@Override
	protected CategoriaProductoCriteria getCriteria() {
		return new CategoriaProductoCriteria();
	}

	@Override
	protected IGenericService<CategoriaProducto, CategoriaProductoCriteria> getService() {
		return ServiceFactory.getCategoriaProductoService();
	}

}
