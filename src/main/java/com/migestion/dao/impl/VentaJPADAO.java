package com.migestion.dao.impl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.dao.IVentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.VentaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.DetalleVenta;
import com.migestion.model.EstadisticaVenta;
import com.migestion.model.Producto;
import com.migestion.model.Venta;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del dao jpa para ventas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
public class VentaJPADAO extends GenericJPADAO<Venta, VentaCriteria> implements IVentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Venta get(Long oid) throws DAOException {

		return getEntityManager().find( Venta.class, oid );
	}


	@Override
	protected QueryBuilder<Venta> getQueryBuilder(Criteria criteria) {
		return new VentaQueryBuilder(criteria);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IVentaDAO#getEstadisticaVenta(com.migestion.services.criteria.VentaCriteria)
	 */
	public EstadisticaVenta getEstadisticaVenta(VentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<Venta> ventaRoot = criteriaQuery.from( Venta.class );
		
		criteriaQuery.select(   builder.array( builder.count( ventaRoot.<Long>get( "oid" ) ),  
								builder.sum( ventaRoot.<Double>get( "monto" ) ),
								builder.sum( ventaRoot.<Double>get( "montoDebe" ) ), 
								builder.sum( ventaRoot.<Double>get( "montoPagado" ) ) ) 
							);
		 
		VentaQueryBuilder queryBuilder = new VentaQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(ventaRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		EstadisticaVenta estadistica = new EstadisticaVenta();		
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
	
	public Boolean hasDependencies(Venta venta){
		
		Boolean ok = false;
		
		//TODO buscamos en pagos?.
		
		return ok;
	}
}
