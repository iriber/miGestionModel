package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.ICajaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CajaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Caja;
import com.migestion.model.EstadisticaCaja;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class CajaJPADAO extends GenericJPADAO<Caja, CajaCriteria> implements ICajaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Caja get(Long oid) throws DAOException {

		return getEntityManager().find( Caja.class, oid );
	}


	@Override
	protected QueryBuilder<Caja> getQueryBuilder(Criteria criteria) {
		return new CajaQueryBuilder(criteria);
	}


	public EstadisticaCaja getEstadisticaCaja(CajaCriteria criteria) {
			
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<Caja> cajaRoot = criteriaQuery.from( Caja.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cajaRoot.<Long>get( "oid" ) ),  
									builder.sum( cajaRoot.<Double>get( "debe" ) ), 
									builder.sum( cajaRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		CajaQueryBuilder queryBuilder = new CajaQueryBuilder(criteria);
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


	public Boolean hasDependencies(Caja entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
