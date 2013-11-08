package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ChequeQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Cheque;
import com.migestion.services.criteria.ChequeCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class ChequeJPADAO extends GenericJPADAO<Cheque, ChequeCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Cheque get(Long oid) throws DAOException {

		return getEntityManager().find( Cheque.class, oid );
	}


	@Override
	protected QueryBuilder<Cheque> getQueryBuilder(Criteria criteria) {
		return new ChequeQueryBuilder(criteria);
	}
}
