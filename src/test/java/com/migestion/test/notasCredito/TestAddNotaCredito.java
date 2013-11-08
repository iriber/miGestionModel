package com.migestion.test.notasCredito;

import java.util.Calendar;
import java.util.Date;

import com.migestion.model.NotaCredito;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.NotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar notas de crédito
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class TestAddNotaCredito extends TestAddEntity<NotaCredito, NotaCreditoCriteria>{


	@Override
	protected NotaCredito getEntity() throws ServiceException {
		NotaCredito c = new NotaCredito();
		c.setNumero("021545454");
		c.setCliente( ServiceFactory.getClienteService().getClienteMostrador() );
		c.setVendedor( ServiceFactory.getVendedorService().getTitularComercio() );
		c.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ) );
		c.setFecha(new Date());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( c.getFecha() );
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		Date fechaVenc = calendar.getTime();
		c.setFechaVencimiento(fechaVenc);
		c.setMonto( 540F );
		return c;
	}

	@Override
	protected NotaCreditoCriteria getCriteria() {
		return new NotaCreditoCriteria();
	}

	@Override
	protected IGenericService<NotaCredito, NotaCreditoCriteria> getService() {
		return ServiceFactory.getNotaCreditoService();
	}

}
