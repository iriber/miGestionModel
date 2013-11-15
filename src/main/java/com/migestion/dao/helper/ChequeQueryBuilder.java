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

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.Cheque;
import com.migestion.services.criteria.ChequeCriteria;
import com.migestion.services.criteria.Criteria;


/**
 * Helper que colabora en la creación de queries para
 * los cheques.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class ChequeQueryBuilder extends QueryBuilder<Cheque>{

	
	public ChequeQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Cheque> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Cheque> query = builder.createQuery(Cheque.class);
	    Root<Cheque> root = query.from(Cheque.class);
	    query.select(root);
	 	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<Cheque> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Date fechaVencimiento = ((ChequeCriteria)criteria).getFechaVencimiento();
		String numero = ((ChequeCriteria)criteria).getNumero();
		String banco = ((ChequeCriteria)criteria).getBanco();
	 
		if( fechaVencimiento!=null ){
			Calendar c = Calendar.getInstance();
			c.setTime( fechaVencimiento );
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.AM_PM, Calendar.AM);

			Date fecha1 = c.getTime();
			
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date fecha2 = c.getTime();
			
	    	Predicate fechaPredicate = builder.between( (root.<Date>get("fecha")), fecha1, fecha2 );
	        predicateList.add(fechaPredicate);
	        
	    }

	    if( !StringUtils.isEmpty(numero) ){
	    	Predicate numeroPredicate = builder.like(
	        builder.upper(root.<String>get("numero")), "%"+numero.toUpperCase()+"%");
	        predicateList.add(numeroPredicate);
	    }
	 	
	    if( !StringUtils.isEmpty(banco) ){
	    	Predicate bancoPredicate = builder.like(
	        builder.upper(root.<String>get("banco")), "%"+banco.toUpperCase()+"%");
	        predicateList.add(bancoPredicate);
	    }
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Cheque> root = query.from(Cheque.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
