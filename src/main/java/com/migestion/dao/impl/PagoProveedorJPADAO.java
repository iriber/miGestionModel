package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.PagoProveedorQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.EstadisticaPago;
import com.migestion.model.PagoProveedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.PagoProveedorCriteria;

/**
 * Implementación del dao jpa para pagos a proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class PagoProveedorJPADAO extends GenericJPADAO<PagoProveedor, PagoProveedorCriteria>  implements IPagoDAO<PagoProveedorCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public PagoProveedor get(Long oid) throws DAOException {

		return getEntityManager().find( PagoProveedor.class, oid );
	}


	@Override
	protected QueryBuilder<PagoProveedor> getQueryBuilder(Criteria criteria) {
		return new PagoProveedorQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IPagoDAO#getEstadisticaPago(com.migestion.services.criteria.PagoCriteria)
	 */
	public EstadisticaPago getEstadisticaPago(PagoProveedorCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<PagoProveedor> pagoRoot = criteriaQuery.from( PagoProveedor.class );
		
		criteriaQuery.select(   builder.array( builder.count( pagoRoot.<Long>get( "oid" ) ),  
								builder.sum( pagoRoot.<Double>get( "monto" ) ) ) 
							);
		 
		PagoProveedorQueryBuilder queryBuilder = new PagoProveedorQueryBuilder(criteria);
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
	
	public Boolean hasDependencies(PagoProveedor pago){
		
		Boolean ok = false;
		
		
		return ok;
	}
}
