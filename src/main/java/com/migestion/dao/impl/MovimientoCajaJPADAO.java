package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCajaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CajaQueryBuilder;
import com.migestion.dao.helper.MovimientoCajaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Caja;
import com.migestion.model.EstadisticaCaja;
import com.migestion.model.MovimientoCaja;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCajaCriteria;

/**
 * Implementación del dao jpa para movimiento de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class MovimientoCajaJPADAO extends GenericJPADAO<MovimientoCaja, MovimientoCajaCriteria> implements IMovimientoCajaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCaja get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCaja.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCaja> getQueryBuilder(Criteria criteria) {
		return new MovimientoCajaQueryBuilder(criteria);
	}


	public EstadisticaCaja getEstadisticaCaja(MovimientoCajaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCaja> cajaRoot = criteriaQuery.from( MovimientoCaja.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cajaRoot.<Long>get( "oid" ) ),  
									builder.sum( cajaRoot.<Double>get( "debe" ) ), 
									builder.sum( cajaRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoCajaQueryBuilder queryBuilder = new MovimientoCajaQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(cajaRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		EstadisticaCaja estadistica = new EstadisticaCaja();		
		estadistica.setCantidad( ((Long)object[0]).intValue() );
		
		if( estadistica.getCantidad() > 0 ){
			estadistica.setImporteTotalDebe( ((Double)object[1]).floatValue() );
			estadistica.setImporteTotalHaber( ((Double)object[2]).floatValue() );
		}else{
			estadistica.setImporteTotalDebe( 0F );
			estadistica.setImporteTotalHaber( 0f );
		}
		
		return estadistica;
	}
}
