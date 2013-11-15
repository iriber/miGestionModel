package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.NotaCreditoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.NotaCredito;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.NotaCreditoCriteria;

/**
 * Implementación del dao jpa para notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class NotaCreditoJPADAO extends GenericJPADAO<NotaCredito, NotaCreditoCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public NotaCredito get(Long oid) throws DAOException {

		return getEntityManager().find( NotaCredito.class, oid );
	}


	@Override
	protected QueryBuilder<NotaCredito> getQueryBuilder(Criteria criteria) {
		return new NotaCreditoQueryBuilder(criteria);
	}
	
	public Boolean hasDependencies(NotaCredito notaCredito){
		
		Boolean ok = false;
		
		
		return ok;
	}
}
