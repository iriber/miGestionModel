
package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.model.MovimientoNotaCredito;
import com.migestion.model.NotaCredito;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.utils.DateHelper;


/**
 * Helper que colabora en la creación de queries para
 * los movimientos de notas de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoNotaCreditoQueryBuilder extends QueryBuilder<MovimientoNotaCredito>{

	
	public MovimientoNotaCreditoQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<MovimientoNotaCredito> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<MovimientoNotaCredito> query = builder.createQuery(MovimientoNotaCredito.class);
	    Root<MovimientoNotaCredito> root = query.from(MovimientoNotaCredito.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<MovimientoNotaCredito> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		NotaCredito notaCredito = ((MovimientoNotaCreditoCriteria)criteria).getNotaCredito();
		Date fechaDesde = ((MovimientoNotaCreditoCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((MovimientoNotaCreditoCriteria)criteria).getFechaHasta();
		Date fecha = ((MovimientoNotaCreditoCriteria)criteria).getFecha();
		
		if( notaCredito!=null ){
	    	Predicate cuentaPredicate = builder.equal( (root.<Date>get("notaCredito")), notaCredito );
	        predicateList.add(cuentaPredicate);
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
		Root<MovimientoNotaCredito> root = query.from(MovimientoNotaCredito.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
