package com.migestion.test.cheques;

import java.util.Date;
import java.util.List;

import com.migestion.model.MovimientoCheque;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar movimientos de cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */
public class TestListMovimientosCheque extends TestListEntities<MovimientoCheque>{

	@Override
	protected List<MovimientoCheque> list() throws ServiceException {
		
		MovimientoChequeCriteria criteria = new MovimientoChequeCriteria();
		criteria.setFecha( new Date() );
		return ServiceFactory.getMovimientoChequeService().list(criteria);
		
	}

}
