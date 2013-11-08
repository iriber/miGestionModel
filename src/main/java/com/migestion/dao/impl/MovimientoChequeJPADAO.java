package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoChequeQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCheque;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;

/**
 * Implementación del dao jpa para movimiento de cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoChequeJPADAO extends GenericJPADAO<MovimientoCheque, MovimientoChequeCriteria> implements IMovimientoCuentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCheque get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCheque.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCheque> getQueryBuilder(Criteria criteria) {
		return new MovimientoChequeQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCheque> chequeRoot = criteriaQuery.from( MovimientoCheque.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( chequeRoot.<Long>get( "oid" ) ),  
									builder.sum( chequeRoot.<Double>get( "debe" ) ), 
									builder.sum( chequeRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoChequeQueryBuilder queryBuilder = new MovimientoChequeQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(chequeRoot, builder, criteria));

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
}
