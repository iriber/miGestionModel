package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoCuentaProveedorQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;
import com.migestion.services.criteria.MovimientoCuentaProveedorCriteria;

/**
 * Implementación del dao jpa para movimiento de cuentas
 * corrientes de proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class MovimientoCuentaProveedorJPADAO extends GenericJPADAO<MovimientoCuentaProveedor, MovimientoCuentaProveedorCriteria> implements IMovimientoCuentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCuentaProveedor get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCuentaProveedor.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCuentaProveedor> getQueryBuilder(Criteria criteria) {
		return new MovimientoCuentaProveedorQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCuentaProveedor> cuentaProveedorRoot = criteriaQuery.from( MovimientoCuentaProveedor.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cuentaProveedorRoot.<Long>get( "oid" ) ),  
									builder.sum( cuentaProveedorRoot.<Double>get( "debe" ) ), 
									builder.sum( cuentaProveedorRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoCuentaProveedorQueryBuilder queryBuilder = new MovimientoCuentaProveedorQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(cuentaProveedorRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		Balance balance = new Balance();		
		balance.setCantidadMovimientos( ((Long)object[0]).intValue() );
		
		if( balance.getCantidadMovimientos() > 0 ){
			balance.setDebe( ((Double)object[1]).floatValue() );
			balance.setHaber( ((Double)object[2]).floatValue() );
		}else{
			balance.setDebe( 0F );
			balance.setHaber( 0f );
		}
		
		return balance;
	}


	public Boolean hasDependencies(MovimientoCuentaProveedor entity)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
