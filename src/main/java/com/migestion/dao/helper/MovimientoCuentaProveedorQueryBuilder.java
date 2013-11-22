
package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.model.Proveedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaProveedorCriteria;
import com.migestion.utils.DateHelper;


/**
 * Helper que colabora en la creación de queries para
 * los movimientos de cuentas corrientes de proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class MovimientoCuentaProveedorQueryBuilder extends QueryBuilder<MovimientoCuentaProveedor>{

	
	public MovimientoCuentaProveedorQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<MovimientoCuentaProveedor> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<MovimientoCuentaProveedor> query = builder.createQuery(MovimientoCuentaProveedor.class);
	    Root<MovimientoCuentaProveedor> root = query.from(MovimientoCuentaProveedor.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<MovimientoCuentaProveedor> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Proveedor proveedor = ((MovimientoCuentaProveedorCriteria)criteria).getProveedor();
		Date fechaDesde = ((MovimientoCuentaProveedorCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((MovimientoCuentaProveedorCriteria)criteria).getFechaHasta();
		Date fecha = ((MovimientoCuentaProveedorCriteria)criteria).getFecha();

		
		if( proveedor!=null ){
	    	Predicate proveedorPredicate = builder.equal( (root.<Date>get("proveedor")), proveedor );
	        predicateList.add(proveedorPredicate);
	    }

	    if( fechaDesde!=null ){
	    	Predicate desdePredicate = builder.greaterThanOrEqualTo( (root.<Date>get("fechaHora")), fechaDesde );
	        predicateList.add(desdePredicate);
	    }
	    if( fechaHasta!=null ){
	    	Predicate hastaPredicate = builder.lessThanOrEqualTo( (root.<Date>get("fechaHora")), fechaHasta );
	        predicateList.add(hastaPredicate);
	    }
		
		if( fecha!=null ){
			
			Date[] fechas = DateHelper.getFechaHoraMinMax(fecha);
			Date fecha1 = fechas[0];
			Date fecha2 = fechas[1];
			
	    	Predicate fechaPredicate = builder.between( (root.<Date>get("fechaHora")), fecha1, fecha2 );
	        predicateList.add(fechaPredicate);
	        
	    }
		
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<MovimientoCuentaProveedor> root = query.from(MovimientoCuentaProveedor.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
