package com.migestion.test.ordenesCompra;

import java.util.Date;

import com.migestion.model.DetalleOrdenCompra;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.OrdenCompra;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.OrdenCompraCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar órdenes de compra
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class TestAddOrdenCompra extends TestAddEntity<OrdenCompra, OrdenCompraCriteria>{


	@Override
	protected OrdenCompra getEntity() throws ServiceException {
		OrdenCompra v = new OrdenCompra();
		
		v.setFecha( new Date() );
		v.setProveedor( ServiceFactory.getProveedorService().get(45L) );
		v.setVendedor( ServiceFactory.getVendedorService().getTitularComercio() );
		v.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ) );
		v.setMonto(30F);
		
		DetalleOrdenCompra d = new DetalleOrdenCompra();
		d.setProducto( ServiceFactory.getProductoService().get(1L));
		d.setCantidad(1);
		d.setCantidadEntregada(0);
		d.setPrecioUnitario(30F);
		
		v.addDetalle(d);
		
		return v;
	}

	@Override
	protected OrdenCompraCriteria getCriteria() {
		return new OrdenCompraCriteria();
	}

	@Override
	protected IGenericService<OrdenCompra, OrdenCompraCriteria> getService() {
		return ServiceFactory.getOrdenCompraService();
	}

}
