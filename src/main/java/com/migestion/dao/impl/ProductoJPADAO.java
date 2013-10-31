package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ProductoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Producto;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.ProductoCriteria;

/**
 * Implementación del dao jpa para productos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class ProductoJPADAO extends GenericJPADAO<Producto, ProductoCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Producto get(Long oid) throws DAOException {

		return getEntityManager().find( Producto.class, oid );
	}


	@Override
	protected QueryBuilder<Producto> getQueryBuilder(Criteria criteria) {
		return new ProductoQueryBuilder(criteria);
	}
}
