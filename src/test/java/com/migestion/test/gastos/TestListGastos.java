package com.migestion.test.gastos;

import java.util.List;

import com.migestion.model.Gasto;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.GastoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar gastos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 */
public class TestListGastos extends TestListEntities<Gasto>{

	@Override
	protected List<Gasto> list() throws ServiceException {
		
		GastoCriteria criteria = new GastoCriteria();
		return ServiceFactory.getGastoService().list(criteria);
		
	}

}
