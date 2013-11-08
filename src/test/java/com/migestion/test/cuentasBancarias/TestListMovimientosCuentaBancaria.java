package com.migestion.test.cuentasBancarias;

import java.util.Date;
import java.util.List;

import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar movimientos de cuentas bancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */
public class TestListMovimientosCuentaBancaria extends TestListEntities<MovimientoCuentaBancaria>{

	@Override
	protected List<MovimientoCuentaBancaria> list() throws ServiceException {
		
		MovimientoCuentaBancariaCriteria criteria = new MovimientoCuentaBancariaCriteria();
		criteria.setFecha( new Date() );
		criteria.setCuentaBancaria( ServiceFactory.getCuentaBancariaService().get(1L));
		return ServiceFactory.getMovimientoCuentaBancariaService().list(criteria);
		
	}

}
