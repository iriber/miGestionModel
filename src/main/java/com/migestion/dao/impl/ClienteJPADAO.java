package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ClienteQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Cliente;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class ClienteJPADAO extends GenericJPADAO<Cliente, ClienteCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Cliente get(Long oid) throws DAOException {

		return getEntityManager().find( Cliente.class, oid );
	}


	@Override
	protected QueryBuilder<Cliente> getQueryBuilder(Criteria criteria) {
		return new ClienteQueryBuilder(criteria);
	}
}
