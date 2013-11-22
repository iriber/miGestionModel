package com.migestion.test.pagos;

import java.util.List;

import com.migestion.model.PagoCliente;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoClienteCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar pagos de clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */
public class TestListPagosCliente extends TestListEntities<PagoCliente>{

	@Override
	protected List<PagoCliente> list() throws ServiceException {
		
		PagoClienteCriteria criteria = new PagoClienteCriteria();
		criteria.setOperacion( ServiceFactory.getVentaService().get(32768L) );
		return ServiceFactory.getPagoClienteService().list(criteria);
		
	}

}
