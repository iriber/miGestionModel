package com.migestion.test.cajas;

import java.util.List;

import com.migestion.model.ConceptoMovimiento;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestListEntities;

/**
 * Test para listar conceptos de movimientos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */
public class TestListConceptosMovimiento extends TestListEntities<ConceptoMovimiento>{

	@Override
	protected List<ConceptoMovimiento> list() throws ServiceException {
		
		ConceptoMovimientoCriteria criteria = new ConceptoMovimientoCriteria();
		return ServiceFactory.getConceptoMovimientoService().list(criteria);
		
	}

}
