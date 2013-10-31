package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CategoriaProductoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.CategoriaProducto;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.CategoriaProductoCriteria;

/**
 * Implementación del dao jpa para categorías de producto
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class CategoriaProductoJPADAO extends GenericJPADAO<CategoriaProducto, CategoriaProductoCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public CategoriaProducto get(Long oid) throws DAOException {

		return getEntityManager().find( CategoriaProducto.class, oid );
	}


	@Override
	protected QueryBuilder<CategoriaProducto> getQueryBuilder(Criteria criteria) {
		return new CategoriaProductoQueryBuilder(criteria);
	}
}
