package com.migestion.test.productos;

import com.migestion.model.CategoriaProducto;
import com.migestion.model.EstadoProducto;
import com.migestion.model.Producto;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ProductoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar productos
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class TestAddProducto extends TestAddEntity<Producto, ProductoCriteria>{


	@Override
	protected Producto getEntity() throws ServiceException {
		Producto p = new Producto();
		p.setNombre("Mantenimiento Red");
		p.setDescripcion("Servicio de manterniento de redes");
		p.setPrecio(150F);
		p.setIva(21F);
		p.setCodigoBarras("3809832432300102");
		p.setEstadoProducto(EstadoProducto.ACTIVO);
		p.setStockActual(0);
		p.setStockMinimo(0);
		p.setStockMaximo(1000);
		p.setObservaciones("");
		CategoriaProducto categoria = ServiceFactory.getCategoriaProductoService().get(1L);
		
		p.setCategoria(categoria);		
		return p;
	}

	@Override
	protected ProductoCriteria getCriteria() {
		return new ProductoCriteria();
	}

	@Override
	protected IGenericService<Producto, ProductoCriteria> getService() {
		return ServiceFactory.getProductoService();
	}

}
