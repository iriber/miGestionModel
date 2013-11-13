package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IGastoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.GastoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.EstadisticaGasto;
import com.migestion.model.Gasto;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.GastoCriteria;

/**
 * Implementación del dao jpa para gastos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
public class GastoJPADAO extends GenericJPADAO<Gasto, GastoCriteria>  implements IGastoDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Gasto get(Long oid) throws DAOException {

		return getEntityManager().find( Gasto.class, oid );
	}


	@Override
	protected QueryBuilder<Gasto> getQueryBuilder(Criteria criteria) {
		return new GastoQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGastoDAO#getEstadisticaGasto(com.migestion.services.criteria.GastoCriteria)
	 */
	public EstadisticaGasto getEstadisticaGasto(GastoCriteria criteria) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<Gasto> gastoRoot = criteriaQuery.from( Gasto.class );
		
		criteriaQuery.select(   builder.array( builder.count( gastoRoot.<Long>get( "oid" ) ),  
								builder.sum( gastoRoot.<Double>get( "monto" ) ) ) 
							);
		 
		GastoQueryBuilder queryBuilder = new GastoQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(gastoRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		EstadisticaGasto estadistica = new EstadisticaGasto();		
		estadistica.setCantidad( ((Long)object[0]).intValue() );
		
		if( estadistica.getCantidad() > 0 ){
			estadistica.setImporteTotal( ((Double)object[1]).floatValue() );
		}else{
			estadistica.setImporteTotal( 0F );
		}
		
		return estadistica;
	}


	public Boolean hasDependencies(Gasto entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
