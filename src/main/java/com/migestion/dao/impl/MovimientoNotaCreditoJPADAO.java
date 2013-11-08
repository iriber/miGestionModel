package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoNotaCreditoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoNotaCredito;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;

/**
 * Implementación del dao jpa para movimiento de notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoNotaCreditoJPADAO extends GenericJPADAO<MovimientoNotaCredito, MovimientoNotaCreditoCriteria> implements IMovimientoCuentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoNotaCredito get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoNotaCredito.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoNotaCredito> getQueryBuilder(Criteria criteria) {
		return new MovimientoNotaCreditoQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoNotaCredito> notaCreditoRoot = criteriaQuery.from( MovimientoNotaCredito.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( notaCreditoRoot.<Long>get( "oid" ) ),  
									builder.sum( notaCreditoRoot.<Double>get( "debe" ) ), 
									builder.sum( notaCreditoRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoNotaCreditoQueryBuilder queryBuilder = new MovimientoNotaCreditoQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(notaCreditoRoot, builder, criteria));

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
