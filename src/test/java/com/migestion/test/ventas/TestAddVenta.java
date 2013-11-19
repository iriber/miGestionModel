package com.migestion.test.ventas;

import java.util.Date;

import com.migestion.model.DetalleVenta;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.Venta;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar ventas
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class TestAddVenta extends TestAddEntity<Venta, VentaCriteria>{


	@Override
	protected Venta getEntity() throws ServiceException {
		Venta v = new Venta();
		
		v.setFecha( new Date() );
		v.setCliente( ServiceFactory.getClienteService().getClienteMostrador() );
		v.setVendedor( ServiceFactory.getVendedorService().getTitularComercio() );
		v.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ) );
		v.setMonto(30F);
		
		DetalleVenta d = new DetalleVenta();
		d.setProducto( ServiceFactory.getProductoService().get(1L));
		d.setCantidad(1);
		d.setPrecioUnitario(30F);
		
		v.addDetalle(d);
		
		return v;
	}

	@Override
	protected VentaCriteria getCriteria() {
		return new VentaCriteria();
	}

	@Override
	protected IGenericService<Venta, VentaCriteria> getService() {
		return ServiceFactory.getVentaService();
	}

}
