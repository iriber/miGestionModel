package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.Producto;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.ProductoCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los productos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class ProductoQueryBuilder extends QueryBuilder<Producto>{

	
	public ProductoQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Producto> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
	    Root<Producto> root = query.from(Producto.class);
	    query.select(root);
	 
	    
	    query.where(getPredicates(root, builder, criteria));		
		
		return query;

	}

	public Predicate[] getPredicates(Root<Producto> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
	    String nombre  = ((ProductoCriteria)criteria).getNombre();
	    if( !StringUtils.isEmpty(nombre) ){
	    	Predicate nombrePredicate = builder.like(
	        builder.upper(root.<String>get("nombre")), "%"+nombre.toUpperCase()+"%");
	        predicateList.add(nombrePredicate);
	    }
	 
	    String nombreEqual  = ((ProductoCriteria)criteria).getNombreEqual();
	    if( !StringUtils.isEmpty(nombreEqual) ){
	    	Predicate nombreEqualPredicate = builder.equal(
	        builder.upper(root.<String>get("nombre")), nombreEqual.toUpperCase());
	        predicateList.add(nombreEqualPredicate);
	    }

	    Long oid = ((ProductoCriteria)criteria).getOid();
	    if( oid!=null ){
	    	Predicate oidPredicate = builder.equal(
	        root.<Long>get("oid"), oid);
	        predicateList.add(oidPredicate);
	    }

	    Long oidNotEqual = ((ProductoCriteria)criteria).getOidNotEqual();
	    if( oidNotEqual!=null ){
	    	Predicate oidNotEqualPredicate = builder.equal(
	        root.<Long>get("oid"), oidNotEqual).not();
	        predicateList.add(oidNotEqualPredicate);
	    }
	    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Producto> root = query.from(Producto.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
