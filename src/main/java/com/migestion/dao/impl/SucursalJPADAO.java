package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.dao.helper.SucursalQueryBuilder;
import com.migestion.model.Sucursal;
import com.migestion.model.Vendedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.SucursalCriteria;

/**
 * Implementación del dao jpa para sucursales
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class SucursalJPADAO extends GenericJPADAO<Sucursal, SucursalCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Sucursal get(Long oid) throws DAOException {

		return getEntityManager().find( Sucursal.class, oid );
	}


	@Override
	protected QueryBuilder<Sucursal> getQueryBuilder(Criteria criteria) {
		return new SucursalQueryBuilder(criteria);
	}
	
	public Boolean hasDependencies(Sucursal sucursal){
		
		Boolean ok = false;
		
		//TODO buscamos en ventas?.
		
		//TODO buscamos en gastos?.
		
		return ok;
	}
}
