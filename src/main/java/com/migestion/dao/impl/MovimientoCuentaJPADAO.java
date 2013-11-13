package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoCuentaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuenta;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;

/**
 * Implementación del dao jpa para movimiento de cuentas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public class MovimientoCuentaJPADAO extends GenericJPADAO<MovimientoCuenta, MovimientoCuentaCriteria> implements IMovimientoCuentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCuenta get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCuenta.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCuenta> getQueryBuilder(Criteria criteria) {
		return new MovimientoCuentaQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCuenta> cuentaBancariaRoot = criteriaQuery.from( MovimientoCuenta.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cuentaBancariaRoot.<Long>get( "oid" ) ),  
									builder.sum( cuentaBancariaRoot.<Double>get( "debe" ) ), 
									builder.sum( cuentaBancariaRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoCuentaQueryBuilder queryBuilder = new MovimientoCuentaQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(cuentaBancariaRoot, builder, criteria));

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


	public Boolean hasDependencies(MovimientoCuenta entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
