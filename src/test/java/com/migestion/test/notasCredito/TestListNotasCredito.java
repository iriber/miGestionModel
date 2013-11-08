package com.migestion.test.notasCredito;

import java.util.List;

import com.migestion.model.NotaCredito;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.NotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 */
public class TestListNotasCredito extends TestListEntities<NotaCredito>{

	@Override
	protected List<NotaCredito> list() throws ServiceException {
		
		NotaCreditoCriteria criteria = new NotaCreditoCriteria();
		return ServiceFactory.getNotaCreditoService().list(criteria);
		
	}

}
