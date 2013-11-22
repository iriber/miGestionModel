package com.migestion.test.pagos;

import java.util.Date;

import com.migestion.model.Cheque;
import com.migestion.model.DetalleFormaPagoCheque;
import com.migestion.model.DetalleFormaPagoEfectivo;
import com.migestion.model.DetallePago;
import com.migestion.model.PagoCliente;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.Venta;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoClienteCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar pagos
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class TestAddPagoCliente extends TestAddEntity<PagoCliente, PagoClienteCriteria>{


	@Override
	protected PagoCliente getEntity() throws ServiceException {
		PagoCliente p = new PagoCliente();
		
		p.setFecha( new Date() );
		p.setCliente( ServiceFactory.getClienteService().getClienteMostrador() );
		p.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ) );
		p.setMonto(30F);
		
		Venta venta = ServiceFactory.getVentaService().get(1L);
		
//		DetalleFormaPagoTarjeta detalleFormaPago = new DetalleFormaPagoTarjeta();
//		detalleFormaPago.setBanco("MACRO");
//		detalleFormaPago.setFechaVencimiento(new Date());
//		detalleFormaPago.setTitular("Roberto Perfumo");
//		detalleFormaPago.setTarjeta("Visa");
//		detalleFormaPago.setNumero("2123123123123");

		
		//pagamos esta venta
		DetallePago detalle = venta.pagate( p.getMonto() );
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
	protected PagoClienteCriteria getCriteria() {
		return new PagoClienteCriteria();
	}

	@Override
	protected IGenericService<PagoCliente, PagoClienteCriteria> getService() {
		return ServiceFactory.getPagoClienteService();
	}

}
