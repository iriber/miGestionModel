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

import com.migestion.model.Cliente;
import com.migestion.model.NotaCredito;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.NotaCreditoCriteria;
import com.migestion.utils.DateHelper;


/**
 * Helper que colabora en la creación de queries para
 * las notas de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class NotaCreditoQueryBuilder extends QueryBuilder<NotaCredito>{

	
	public NotaCreditoQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<NotaCredito> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<NotaCredito> query = builder.createQuery(NotaCredito.class);
	    Root<NotaCredito> root = query.from(NotaCredito.class);
	    query.select(root);
	 	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<NotaCredito> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Date fecha = ((NotaCreditoCriteria)criteria).getFecha();
		Cliente cliente = ((NotaCreditoCriteria)criteria).getCliente();
	 
		if( fecha!=null ){
			
			Date[] fechas = DateHelper.getFechaHoraMinMax(fecha);
			Date fecha1 = fechas[0];
			Date fecha2 = fechas[1];
			
	    	Predicate fechaPredicate = builder.between( (root.<Date>get("fecha")), fecha1, fecha2 );
	        predicateList.add(fechaPredicate);
	        
	    }

	    
	    if( cliente!=null ){
	    	Predicate clientePredicate = builder.equal( (root.<Cliente>get("cliente")), cliente );
	        predicateList.add(clientePredicate);
	    }
	    
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<NotaCredito> root = query.from(NotaCredito.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
