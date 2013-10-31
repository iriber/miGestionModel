package com.migestion.test.productos;


import java.util.List;

import com.migestion.model.Producto;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ProductoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar productos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class TestListProductos extends TestListEntities<Producto> {


	protected List<Producto> list() throws ServiceException{
		
		ProductoCriteria criteria = new ProductoCriteria();
		return ServiceFactory.getProductoService().list(criteria);
	}

}
