package com.migestion.test.cuentasBancarias;

import java.util.List;

import com.migestion.model.CuentaBancaria;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar cuentas bancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */
public class TestListCuentasBancarias extends TestListEntities<CuentaBancaria>{

	@Override
	protected List<CuentaBancaria> list() throws ServiceException {
		
		CuentaBancariaCriteria criteria = new CuentaBancariaCriteria();
		return ServiceFactory.getCuentaBancariaService().list(criteria);
		
	}

}
