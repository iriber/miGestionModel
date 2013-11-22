package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IOperacionDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.OrdenCompraQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.EstadisticaOperacion;
import com.migestion.model.OrdenCompra;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.OperacionCriteria;
import com.migestion.services.criteria.OrdenCompraCriteria;

/**
 * Implementación del dao jpa para órdenes de compra
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */
public class OrdenCompraJPADAO extends GenericJPADAO<OrdenCompra, OrdenCompraCriteria> implements IOperacionDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public OrdenCompra get(Long oid) throws DAOException {

		return getEntityManager().find( OrdenCompra.class, oid );
	}


	@Override
	protected QueryBuilder<OrdenCompra> getQueryBuilder(Criteria criteria) {
		return new OrdenCompraQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IOperacionDAO#getEstadisticaOperacion(com.migestion.services.criteria.OperacionCriteria)
	 */
	public EstadisticaOperacion getEstadisticaOperacion(OperacionCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<OrdenCompra> ventaRoot = criteriaQuery.from( OrdenCompra.class );
		
		criteriaQuery.select(   builder.array( builder.count( ventaRoot.<Long>get( "oid" ) ),  
								builder.sum( ventaRoot.<Double>get( "monto" ) ),
								builder.sum( ventaRoot.<Double>get( "montoDebe" ) ), 
								builder.sum( ventaRoot.<Double>get( "montoPagado" ) ) ) 
							);
		 
		OrdenCompraQueryBuilder queryBuilder = new OrdenCompraQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(ventaRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		EstadisticaOperacion estadistica = new EstadisticaOperacion();		
		estadistica.setCantidad( ((Long)object[0]).intValue() );
		
		if( estadistica.getCantidad() > 0 ){
			estadistica.setImporteTotal( ((Double)object[1]).floatValue() );
			estadistica.setImporteTotalDebe( ((Double)object[2]).floatValue() );
			estadistica.setImporteTotalPagado(((Double)object[3]).floatValue() );
		}else{
			estadistica.setImporteTotal( 0F );
			estadistica.setImporteTotalDebe( 0F );
			estadistica.setImporteTotalPagado( 0f );
		}
		
		return estadistica;
	}
	
	public Boolean hasDependencies(OrdenCompra venta){
		
		Boolean ok = false;
		
		//TODO buscamos en pagos?.
		
		return ok;
	}
}
