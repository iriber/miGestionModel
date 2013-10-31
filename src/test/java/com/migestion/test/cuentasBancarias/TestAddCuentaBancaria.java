package com.migestion.test.cuentasBancarias;

import com.migestion.model.CuentaBancaria;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar cuentas bancarias
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class TestAddCuentaBancaria extends TestAddEntity<CuentaBancaria, CuentaBancariaCriteria>{


	@Override
	protected CuentaBancaria getEntity() throws ServiceException {
		CuentaBancaria c = new CuentaBancaria();
		c.setNombre("BANCO MACRO");
		c.setTitular("Bernardo Iribarne");
		c.setCuit("20-28070832-2");
		c.setCbu("2850000340094048023378");
		c.setNroCuenta("CA - PESOS - 400009404802337");
		c.setSucursal("540 - Casa Central");
		return c;
	}

	@Override
	protected CuentaBancariaCriteria getCriteria() {
		return new CuentaBancariaCriteria();
	}

	@Override
	protected IGenericService<CuentaBancaria, CuentaBancariaCriteria> getService() {
		return ServiceFactory.getCuentaBancariaService();
	}

}
