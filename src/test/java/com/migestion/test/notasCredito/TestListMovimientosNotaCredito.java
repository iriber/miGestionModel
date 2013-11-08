package com.migestion.test.notasCredito;

import java.util.Date;
import java.util.List;

import com.migestion.model.MovimientoNotaCredito;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar movimientos de notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */
public class TestListMovimientosNotaCredito extends TestListEntities<MovimientoNotaCredito>{

	@Override
	protected List<MovimientoNotaCredito> list() throws ServiceException {
		
		MovimientoNotaCreditoCriteria criteria = new MovimientoNotaCreditoCriteria();
		criteria.setFecha( new Date() );
		return ServiceFactory.getMovimientoNotaCreditoService().list(criteria);
		
	}

}
