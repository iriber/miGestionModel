package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.Vendedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.VendedorCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los vendedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 *
 */
public class VendedorQueryBuilder extends QueryBuilder<Vendedor>{

	
	public VendedorQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Vendedor> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Vendedor> query = builder.createQuery(Vendedor.class);
	    Root<Vendedor> root = query.from(Vendedor.class);
	    query.select(root);
	 
	    query.where(getPredicates(root, builder, criteria));		

	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<Vendedor> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
	    String nombre  = ((VendedorCriteria)criteria).getNombre();
	    String nombreEqual  = ((VendedorCriteria)criteria).getNombreEqual();
	    Long oid = ((VendedorCriteria)criteria).getOid();
	    Long oidNotEqual = ((VendedorCriteria)criteria).getOidNotEqual();
	    Long nroDocumento = ((VendedorCriteria)criteria).getNroDocumento();
	    
	    
	    if( !StringUtils.isEmpty(nombre) ){
	    	Predicate nombrePredicate = builder.like(
	        builder.upper(root.<String>get("nombre")), "%"+nombre.toUpperCase()+"%");
	        predicateList.add(nombrePredicate);
	    }
	    if( !StringUtils.isEmpty(nombreEqual) ){
	    	Predicate nombreEqualPredicate = builder.equal(
	        builder.upper(root.<String>get("nombre")), nombreEqual.toUpperCase());
	        predicateList.add(nombreEqualPredicate);
	    }

	    if( oid!=null ){
	    	Predicate oidPredicate = builder.equal(
	        root.<Long>get("oid"), oid);
	        predicateList.add(oidPredicate);
	    }

	    if( oidNotEqual!=null ){
	    	Predicate oidNotEqualPredicate = builder.equal(
	        root.<Long>get("oid"), oidNotEqual).not();
	        predicateList.add(oidNotEqualPredicate);
	    }	    
	 
	    if( nroDocumento!=null ){
	    	Predicate nroDocumentoPredicate = builder.equal(
	        root.<Long>get("nroDocumento"), nroDocumento);
	        predicateList.add(nroDocumentoPredicate);
	    }
	 
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Vendedor> root = query.from(Vendedor.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
