package com.migestion.dao.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.model.EstadoOrdenCompra;
import com.migestion.model.OrdenCompra;
import com.migestion.model.Proveedor;
import com.migestion.model.Vendedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.OrdenCompraCriteria;


/**
 * Helper que colabora en la creación de queries para
 * órdenes de compra.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */
public class OrdenCompraQueryBuilder extends QueryBuilder<OrdenCompra>{

	
	public OrdenCompraQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<OrdenCompra> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<OrdenCompra> query = builder.createQuery(OrdenCompra.class);
	    Root<OrdenCompra> root = query.from(OrdenCompra.class);
	    query.select(root);
	 	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<OrdenCompra> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Date fechaDesde = ((OrdenCompraCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((OrdenCompraCriteria)criteria).getFechaHasta();
		Proveedor proveedor = ((OrdenCompraCriteria)criteria).getProveedor();
		Vendedor vendedor = ((OrdenCompraCriteria)criteria).getVendedor();
		Collection<EstadoOrdenCompra> estados = ((OrdenCompraCriteria)criteria).getEstados();
		Collection<EstadoOrdenCompra> estadosExcluir = ((OrdenCompraCriteria)criteria).getEstadosExcluir();
	 
	    if( fechaDesde!=null ){
	    	Predicate desdePredicate = builder.greaterThanOrEqualTo( (root.<Date>get("fecha")), fechaDesde );
	        predicateList.add(desdePredicate);
	    }
	    if( fechaHasta!=null ){
	    	Predicate hastaPredicate = builder.lessThanOrEqualTo( (root.<Date>get("fecha")), fechaHasta );
	        predicateList.add(hastaPredicate);
	    }
	    
	    if( proveedor!=null ){
	    	Predicate proveedorPredicate = builder.equal( (root.<Proveedor>get("proveedor")), proveedor );
	        predicateList.add(proveedorPredicate);
	    }
	    
	    if( vendedor!=null ){
	    	Predicate vendedorPredicate = builder.equal( (root.<Vendedor>get("vendedor")), vendedor );
	        predicateList.add(vendedorPredicate);
	    }
	    
	    if( estados != null && estados.size()>0 ){
	    	
	    	Predicate estadoPredicate = root.<EstadoOrdenCompra>get("estadoOrdenCompra").in(estados);
	    	predicateList.add(estadoPredicate);
	    }

	    if( estadosExcluir != null && estadosExcluir.size()>0 ){
	    	
	    	Predicate estadoPredicate = (root.<EstadoOrdenCompra>get("estadoOrdenCompra").in(estadosExcluir)).not();
	    	predicateList.add(estadoPredicate);
	    }
		
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
		return predicates;
	}	

	@Override
	public CriteriaQuery<Long> buildToCount(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<OrdenCompra> root = query.from(OrdenCompra.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
