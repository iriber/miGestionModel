package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.VendedorQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Vendedor;
import com.migestion.model.Venta;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para vendedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class VendedorJPADAO extends GenericJPADAO<Vendedor, VendedorCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Vendedor get(Long oid) throws DAOException {

		return getEntityManager().find( Vendedor.class, oid );
	}


	@Override
	protected QueryBuilder<Vendedor> getQueryBuilder(Criteria criteria) {
		return new VendedorQueryBuilder(criteria);
	}
	
	public Boolean hasDependencies(Vendedor vendedor){
		
		Boolean ok = false;
		
		//TODO buscamos en ventas?.
		
		//TODO buscamos en gastos?.
		
		return ok;
	}
}
