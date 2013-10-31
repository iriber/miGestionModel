package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.PagoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.EstadisticaPago;
import com.migestion.model.Pago;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.PagoCriteria;

/**
 * Implementación del dao jpa para pagos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class PagoJPADAO extends GenericJPADAO<Pago, PagoCriteria>  implements IPagoDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Pago get(Long oid) throws DAOException {

		return getEntityManager().find( Pago.class, oid );
	}


	@Override
	protected QueryBuilder<Pago> getQueryBuilder(Criteria criteria) {
		return new PagoQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IPagoDAO#getEstadisticaPago(com.migestion.services.criteria.PagoCriteria)
	 */
	public EstadisticaPago getEstadisticaPago(PagoCriteria criteria) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<Pago> pagoRoot = criteriaQuery.from( Pago.class );
		
		criteriaQuery.select(   builder.array( builder.count( pagoRoot.<Long>get( "oid" ) ),  
								builder.sum( pagoRoot.<Double>get( "monto" ) ) ) 
							);
		 
		PagoQueryBuilder queryBuilder = new PagoQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(pagoRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		EstadisticaPago estadistica = new EstadisticaPago();		
		estadistica.setCantidad( ((Long)object[0]).intValue() );
		
		if( estadistica.getCantidad() > 0 ){
			estadistica.setImporteTotal( ((Double)object[1]).floatValue() );
		}else{
			estadistica.setImporteTotal( 0F );
		}
		
		return estadistica;
	}
}
