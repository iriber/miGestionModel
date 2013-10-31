package com.migestion.test.cajas;

import java.util.Date;

import com.migestion.model.MovimientoCaja;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar movimientos de caja
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class TestAddMovimientoCaja extends TestAddEntity<MovimientoCaja, MovimientoCajaCriteria>{


	@Override
	protected MovimientoCaja getEntity() throws ServiceException {
		MovimientoCaja c = new MovimientoCaja();
		c.setCaja( ServiceFactory.getCajaService().get(1L));
		c.setFechaHora( new Date() );
		c.setHaber(50F);
		c.setConcepto( ServiceFactory.getConceptoCajaService().get(1L));
		return c;
	}

	@Override
	protected MovimientoCajaCriteria getCriteria() {
		return new MovimientoCajaCriteria();
	}

	@Override
	protected IGenericService<MovimientoCaja, MovimientoCajaCriteria> getService() {
		return ServiceFactory.getMovimientoCajaService();
	}

}
