package com.migestion.test.gastos;

import java.util.Date;

import com.migestion.model.Cheque;
import com.migestion.model.Gasto;
import com.migestion.model.GastoEfectivo;
import com.migestion.model.Venta;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.GastoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar gastos
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public class TestAddGasto extends TestAddEntity<Gasto, GastoCriteria>{


	@Override
	protected Gasto getEntity() throws ServiceException {

		GastoEfectivo gasto = new GastoEfectivo();
		
		gasto.setFecha( new Date() );
		gasto.setSucursal( ServiceFactory.getSucursalService().get(1L) );
		gasto.setMonto(1800F);
		gasto.setCaja(ServiceFactory.getCajaService().get(6L));
		gasto.setConcepto( ServiceFactory.getConceptoMovimientoService().get(4L) );
		return gasto;
	}

	@Override
	protected GastoCriteria getCriteria() {
		return new GastoCriteria();
	}

	@Override
	protected IGenericService<Gasto, GastoCriteria> getService() {
		return ServiceFactory.getGastoService();
	}

}
