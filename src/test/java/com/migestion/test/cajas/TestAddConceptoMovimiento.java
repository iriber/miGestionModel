package com.migestion.test.cajas;

import com.migestion.model.ConceptoMovimiento;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar conceptos de caja
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class TestAddConceptoMovimiento extends TestAddEntity<ConceptoMovimiento, ConceptoMovimientoCriteria>{


	@Override
	protected ConceptoMovimiento getEntity() throws ServiceException {
		ConceptoMovimiento c = new ConceptoMovimiento();
		//c.setNombre("Saldo Inicial");
		c.setNombre("Pago de venta");
		//c.setNombre("Ventas");
		return c;
	}

	@Override
	protected ConceptoMovimientoCriteria getCriteria() {
		return new ConceptoMovimientoCriteria();
	}

	@Override
	protected IGenericService<ConceptoMovimiento, ConceptoMovimientoCriteria> getService() {
		return ServiceFactory.getConceptoMovimientoService();
	}

}
