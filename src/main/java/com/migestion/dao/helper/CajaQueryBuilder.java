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

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.Caja;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.criteria.Criteria;


/**
 * Helper que colabora en la creación de queries para
 * las cajas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class CajaQueryBuilder extends QueryBuilder<Caja>{

	
	public CajaQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Caja> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Caja> query = builder.createQuery(Caja.class);
	    Root<Caja> root = query.from(Caja.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<Caja> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
	    String numero = ((CajaCriteria)criteria).getNumero();
	    if( !StringUtils.isEmpty(numero) ){
	    	Predicate numeroPredicate = builder.equal(
	        builder.upper(root.<String>get("numero")), numero.toUpperCase());
	        predicateList.add(numeroPredicate);
	    }
	 
		Date fecha = ((CajaCriteria)criteria).getFecha();
		
		if( fecha!=null ){
			Calendar c = Calendar.getInstance();
			c.setTime( fecha );
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.AM_PM, Calendar.AM);
			
			Date fecha1 = c.getTime();
			
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.AM_PM, Calendar.PM);
			Date fecha2 = c.getTime();
			
			
	    	Predicate fechaPredicate = builder.between( (root.<Date>get("fecha")), fecha1, fecha2 );
	        predicateList.add(fechaPredicate);
	        
	        System.out.println( " F1:" + new SimpleDateFormat("yyyy/MM/dd H:m").format(fecha) );
	        System.out.println( " F2:" + new SimpleDateFormat("yyyy/MM/dd H:m").format(fecha2) );
	        
	    }
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Caja> root = query.from(Caja.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
