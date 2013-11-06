package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.CuentaBancaria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.CuentaBancariaCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los bancos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class CuentaBancariaQueryBuilder extends QueryBuilder<CuentaBancaria>{

	
	public CuentaBancariaQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<CuentaBancaria> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<CuentaBancaria> query = builder.createQuery(CuentaBancaria.class);
	    Root<CuentaBancaria> root = query.from(CuentaBancaria.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
		return query;

	}

	public Predicate[] getPredicates(Root<CuentaBancaria> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
	    String nombre  = ((CuentaBancariaCriteria)criteria).getNombre();
	    if( !StringUtils.isEmpty(nombre) ){
	    	Predicate nombrePredicate = builder.like(
	        builder.upper(root.<String>get("nombre")), "%"+nombre.toUpperCase()+"%");
	        predicateList.add(nombrePredicate);
	    }
	 
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CuentaBancaria> root = query.from(CuentaBancaria.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
