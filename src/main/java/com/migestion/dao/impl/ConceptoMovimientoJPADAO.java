package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ConceptoMovimientoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para conceptos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class ConceptoMovimientoJPADAO extends GenericJPADAO<ConceptoMovimiento, ConceptoMovimientoCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public ConceptoMovimiento get(Long oid) throws DAOException {

		return getEntityManager().find( ConceptoMovimiento.class, oid );
	}


	@Override
	protected QueryBuilder<ConceptoMovimiento> getQueryBuilder(Criteria criteria) {
		return new ConceptoMovimientoQueryBuilder(criteria);
	}


	public Boolean hasDependencies(ConceptoMovimiento entity)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
