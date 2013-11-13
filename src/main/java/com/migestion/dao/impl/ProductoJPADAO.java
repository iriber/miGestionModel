package com.migestion.dao.impl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ProductoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Cliente;
import com.migestion.model.DetalleVenta;
import com.migestion.model.Producto;
import com.migestion.model.Venta;
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
	
	public Boolean hasDependencies(Producto producto){
		
		Boolean ok = false;
		
		//buscamos en los detalles de venta.
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<DetalleVenta> root = criteriaQuery.from(DetalleVenta.class);
		criteriaQuery.select( builder.count( root.<Long>get( "oid" ) ) );
		Predicate productoPredicate = builder.equal( (root.<Producto>get("producto")), producto );
		criteriaQuery.where( productoPredicate );
		Query query = getEntityManager().createQuery(criteriaQuery);
		ok = (Long)query.getSingleResult() > 0;
		
		
		return ok;
	}
}
