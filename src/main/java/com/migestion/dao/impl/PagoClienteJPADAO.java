package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.PagoClienteQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.EstadisticaPago;
import com.migestion.model.PagoCliente;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.PagoClienteCriteria;

/**
 * Implementación del dao jpa para pagos de clienets
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class PagoClienteJPADAO extends GenericJPADAO<PagoCliente, PagoClienteCriteria>  implements IPagoDAO<PagoClienteCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public PagoCliente get(Long oid) throws DAOException {

		return getEntityManager().find( PagoCliente.class, oid );
	}


	@Override
	protected QueryBuilder<PagoCliente> getQueryBuilder(Criteria criteria) {
		return new PagoClienteQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IPagoDAO#getEstadisticaPago(com.migestion.services.criteria.PagoCriteria)
	 */
	public EstadisticaPago getEstadisticaPago(PagoClienteCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<PagoCliente> pagoRoot = criteriaQuery.from( PagoCliente.class );
		
		criteriaQuery.select(   builder.array( builder.count( pagoRoot.<Long>get( "oid" ) ),  
								builder.sum( pagoRoot.<Double>get( "monto" ) ) ) 
							);
		 
		PagoClienteQueryBuilder queryBuilder = new PagoClienteQueryBuilder(criteria);
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
	
	public Boolean hasDependencies(PagoCliente pago){
		
		Boolean ok = false;
		
		
		return ok;
	}
}
