package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaBancariaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoCuentaBancariaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.CuentaBancaria;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;

/**
 * Implementación del dao jpa para movimiento de cuentas bancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoCuentaBancariaJPADAO extends GenericJPADAO<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria> implements IMovimientoCuentaBancariaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCuentaBancaria get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCuentaBancaria.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCuentaBancaria> getQueryBuilder(Criteria criteria) {
		return new MovimientoCuentaBancariaQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCuentaBancaria> cuentaBancariaRoot = criteriaQuery.from( MovimientoCuentaBancaria.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cuentaBancariaRoot.<Long>get( "oid" ) ),  
									builder.sum( cuentaBancariaRoot.<Double>get( "debe" ) ), 
									builder.sum( cuentaBancariaRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoCuentaBancariaQueryBuilder queryBuilder = new MovimientoCuentaBancariaQueryBuilder(criteria);
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


	public Boolean hasDependencies(MovimientoCuentaBancaria entity)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void delete(CuentaBancaria cuentaBancaria){
		
		String hql = "delete from MovimientoCuentaBancaria where cuentaBancaria= :cta";
		getEntityManager().createQuery(hql).setParameter("cta", cuentaBancaria).executeUpdate();
		
	}
}
