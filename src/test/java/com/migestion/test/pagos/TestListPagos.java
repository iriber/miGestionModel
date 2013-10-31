package com.migestion.test.pagos;

import java.util.List;

import com.migestion.model.Pago;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar pagos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */
public class TestListPagos extends TestListEntities<Pago>{

	@Override
	protected List<Pago> list() throws ServiceException {
		
		PagoCriteria criteria = new PagoCriteria();
		criteria.setOperacion( ServiceFactory.getVentaService().get(32768L) );
		return ServiceFactory.getPagoService().list(criteria);
		
	}

}
