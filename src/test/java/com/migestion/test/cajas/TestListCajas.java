package com.migestion.test.cajas;

import java.util.List;

import com.migestion.model.Caja;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar cajas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */
public class TestListCajas extends TestListEntities<Caja>{

	@Override
	protected List<Caja> list() throws ServiceException {
		
		CajaCriteria criteria = new CajaCriteria();
		return ServiceFactory.getCajaService().list(criteria);
		
	}

}
