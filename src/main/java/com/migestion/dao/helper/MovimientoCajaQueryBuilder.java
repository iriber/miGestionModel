
package com.migestion.dao.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.model.Caja;
import com.migestion.model.MovimientoCaja;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCajaCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los movimientos de caja.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class MovimientoCajaQueryBuilder extends QueryBuilder<MovimientoCaja>{

	
	public MovimientoCajaQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<MovimientoCaja> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<MovimientoCaja> query = builder.createQuery(MovimientoCaja.class);
	    Root<MovimientoCaja> root = query.from(MovimientoCaja.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<MovimientoCaja> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Caja caja = ((MovimientoCajaCriteria)criteria).getCaja();
		if( caja!=null ){
	    	Predicate fechaPredicate = builder.equal( (root.<Date>get("caja")), caja );
	        predicateList.add(fechaPredicate);
	    }

		Date fecha = ((MovimientoCajaCriteria)criteria).getFecha();
		if( fecha!=null ){
			Calendar c = Calendar.getInstance();
			c.setTime( fecha );
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.AM_PM, Calendar.AM);

			Date fecha1 = c.getTime();
			
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date fecha2 = c.getTime();
			
	    	Predicate fechaPredicate = builder.between( (root.<Date>get("fechaHora")), fecha1, fecha2 );
	        predicateList.add(fechaPredicate);
	        
	        System.out.println( " F:" + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(fecha) );
	        System.out.println( " F1:" + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(fecha1) );
	        System.out.println( " F2:" + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(fecha2) );
	        
	    }
		
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<MovimientoCaja> root = query.from(MovimientoCaja.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
