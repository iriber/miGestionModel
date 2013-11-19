package com.migestion.test.cajas;

import java.util.Date;

import com.migestion.model.Caja;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar cajas
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class TestAddCaja extends TestAddEntity<Caja, CajaCriteria>{


	@Override
	protected Caja getEntity() throws ServiceException {
		Caja c = new Caja();
		c.setNumero("0000000001");
		c.setCajero( ServiceFactory.getVendedorService().getTitularComercio());
		c.setFecha( new Date() );
		c.setSaldoInicial(500F);
		c.setSucursal( ServiceFactory.getSucursalService().get(ValoresPredefinidos.SUCURSAL_CASA_MATRIZ));
		return c;
	}

	@Override
	protected CajaCriteria getCriteria() {
		return new CajaCriteria();
	}

	@Override
	protected IGenericService<Caja, CajaCriteria> getService() {
		return ServiceFactory.getCajaService();
	}

}
