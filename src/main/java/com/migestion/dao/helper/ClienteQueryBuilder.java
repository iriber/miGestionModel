package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.migestion.model.Cliente;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.ProductoCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los clientes.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class ClienteQueryBuilder extends QueryBuilder<Cliente>{

	
	public ClienteQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Cliente> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
	    Root<Cliente> root = query.from(Cliente.class);
	    query.select(root);
	 
	    query.where(getPredicates(root, builder, criteria));		
	    query.orderBy( criteria.buildOrderBy( root, builder ));		
		return query;

	}

	public Predicate[] getPredicates(Root<Cliente> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
	    String nombre  = ((ClienteCriteria)criteria).getNombre();
	    String nombreEqual  = ((ClienteCriteria)criteria).getNombreEqual();
	    Long oid = ((ClienteCriteria)criteria).getOid();
	    Long oidNotEqual = ((ClienteCriteria)criteria).getOidNotEqual();
	    Long nroDocumento = ((ClienteCriteria)criteria).getNroDocumento();
	    
	    
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
		Root<Cliente> root = query.from(Cliente.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
