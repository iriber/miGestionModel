
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

import com.migestion.model.CuentaBancaria;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.utils.DateHelper;


/**
 * Helper que colabora en la creación de queries para
 * los movimientos de cuentas bancarias.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoCuentaBancariaQueryBuilder extends QueryBuilder<MovimientoCuentaBancaria>{

	
	public MovimientoCuentaBancariaQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<MovimientoCuentaBancaria> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<MovimientoCuentaBancaria> query = builder.createQuery(MovimientoCuentaBancaria.class);
	    Root<MovimientoCuentaBancaria> root = query.from(MovimientoCuentaBancaria.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<MovimientoCuentaBancaria> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		CuentaBancaria cuentaBancaria = ((MovimientoCuentaBancariaCriteria)criteria).getCuentaBancaria();
		Date fechaDesde = ((MovimientoCuentaBancariaCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((MovimientoCuentaBancariaCriteria)criteria).getFechaHasta();
		Date fecha = ((MovimientoCuentaBancariaCriteria)criteria).getFecha();

		
		if( cuentaBancaria!=null ){
	    	Predicate cuentaPredicate = builder.equal( (root.<Date>get("cuentaBancaria")), cuentaBancaria );
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
		Root<MovimientoCuentaBancaria> root = query.from(MovimientoCuentaBancaria.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
