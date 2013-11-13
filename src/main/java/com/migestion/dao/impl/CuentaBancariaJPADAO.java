package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CuentaBancariaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.CuentaBancaria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.CuentaBancariaCriteria;

/**
 * Implementación del dao jpa para cuentasBancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class CuentaBancariaJPADAO extends GenericJPADAO<CuentaBancaria, CuentaBancariaCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public CuentaBancaria get(Long oid) throws DAOException {

		return getEntityManager().find( CuentaBancaria.class, oid );
	}


	@Override
	protected QueryBuilder<CuentaBancaria> getQueryBuilder(Criteria criteria) {
		return new CuentaBancariaQueryBuilder(criteria);
	}


	public Boolean hasDependencies(CuentaBancaria entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
