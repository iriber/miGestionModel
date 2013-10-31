package com.migestion.test.productos;


import java.util.List;

import com.migestion.model.CategoriaProducto;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CategoriaProductoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar categor'ias de productos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class TestListCategoriasProducto extends TestListEntities<CategoriaProducto> {


	protected List<CategoriaProducto> list() throws ServiceException{
		
		CategoriaProductoCriteria criteria = new CategoriaProductoCriteria();
		return ServiceFactory.getCategoriaProductoService().list(criteria);
	}

}
