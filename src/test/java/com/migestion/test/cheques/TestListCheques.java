package com.migestion.test.cheques;

import java.util.List;

import com.migestion.model.Cheque;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ChequeCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */
public class TestListCheques extends TestListEntities<Cheque>{

	@Override
	protected List<Cheque> list() throws ServiceException {
		
		ChequeCriteria criteria = new ChequeCriteria();
		return ServiceFactory.getChequeService().list(criteria);
		
	}

}
