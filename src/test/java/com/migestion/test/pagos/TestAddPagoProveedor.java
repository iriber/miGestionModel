package com.migestion.test.pagos;

import java.util.Date;

import com.migestion.model.Cheque;
import com.migestion.model.DetalleFormaPagoCheque;
import com.migestion.model.DetalleFormaPagoEfectivo;
import com.migestion.model.DetallePago;
import com.migestion.model.OrdenCompra;
import com.migestion.model.PagoProveedor;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoProveedorCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar pago a proveedor
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class TestAddPagoProveedor extends TestAddEntity<PagoProveedor, PagoProveedorCriteria>{


	@Override
	protected PagoProveedor getEntity() throws ServiceException {
		PagoProveedor p = new PagoProveedor();
		
		p.setFecha( new Date() );
		p.setProveedor( ServiceFactory.getProveedorService().get(45L) );
		p.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ) );
		p.setMonto(30F);
		
		OrdenCompra o = ServiceFactory.getOrdenCompraService().get(1L);
		
//		DetalleFormaPagoTarjeta detalleFormaPago = new DetalleFormaPagoTarjeta();
//		detalleFormaPago.setBanco("MACRO");
//		detalleFormaPago.setFechaVencimiento(new Date());
//		detalleFormaPago.setTitular("Roberto Perfumo");
//		detalleFormaPago.setTarjeta("Visa");
//		detalleFormaPago.setNumero("2123123123123");

		
		//pagamos la orden de compra
		DetallePago detalle = o.pagate( p.getMonto() );
		p.addDetalle( detalle );
		
		
		//pagamos una parte en cheque y otra en efectivo
		DetalleFormaPagoCheque detalleCheque = new DetalleFormaPagoCheque();
		Cheque cheque = new Cheque();
		cheque.setFechaVencimiento(new Date());
		cheque.setMonto( 20F );
		cheque.setNumero("2123123123123");
		cheque.setBanco("Galicia");
		detalleCheque.setCheque(cheque);
		p.addDetalle(detalleCheque);

		DetalleFormaPagoEfectivo detalleEfectivo = new DetalleFormaPagoEfectivo();
		detalleEfectivo.setMonto(10F);
		detalleEfectivo.setCaja( ServiceFactory.getCajaService().get(1L));
		p.addDetalle(detalleEfectivo);
	
		return p;
	}

	@Override
	protected PagoProveedorCriteria getCriteria() {
		return new PagoProveedorCriteria();
	}

	@Override
	protected IGenericService<PagoProveedor, PagoProveedorCriteria> getService() {
		return ServiceFactory.getPagoProveedorService();
	}

}
