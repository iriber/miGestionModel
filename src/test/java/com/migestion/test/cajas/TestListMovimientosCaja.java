package com.migestion.test.cajas;

import java.util.List;

import com.migestion.model.MovimientoCaja;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar movimientos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */
public class TestListMovimientosCaja extends TestListEntities<MovimientoCaja>{

	@Override
	protected List<MovimientoCaja> list() throws ServiceException {
		
		MovimientoCajaCriteria criteria = new MovimientoCajaCriteria();
		//criteria.setCaja( ServiceFactory.getCajaService().get(1L));
		return ServiceFactory.getMovimientoCajaService().list(criteria);
		
	}

}
