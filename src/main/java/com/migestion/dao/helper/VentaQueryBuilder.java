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

import com.migestion.model.Cliente;
import com.migestion.model.EstadoVenta;
import com.migestion.model.Vendedor;
import com.migestion.model.Venta;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.VentaCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los ventas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
public class VentaQueryBuilder extends QueryBuilder<Venta>{

	
	public VentaQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<Venta> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Venta> query = builder.createQuery(Venta.class);
	    Root<Venta> root = query.from(Venta.class);
	    query.select(root);
	 	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<Venta> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		 
		Date fechaDesde = ((VentaCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((VentaCriteria)criteria).getFechaHasta();
		Cliente cliente = ((VentaCriteria)criteria).getCliente();
		Vendedor vendedor = ((VentaCriteria)criteria).getVendedor();
		Collection<EstadoVenta> estados = ((VentaCriteria)criteria).getEstados();
		Collection<EstadoVenta> estadosExcluir = ((VentaCriteria)criteria).getEstadosExcluir();
	 
	    if( fechaDesde!=null ){
	    	Predicate desdePredicate = builder.greaterThanOrEqualTo( (root.<Date>get("fecha")), fechaDesde );
	        predicateList.add(desdePredicate);
	    }
	    if( fechaHasta!=null ){
	    	Predicate hastaPredicate = builder.lessThanOrEqualTo( (root.<Date>get("fecha")), fechaHasta );
	        predicateList.add(hastaPredicate);
	    }
	    
	    if( cliente!=null ){
	    	Predicate clientePredicate = builder.equal( (root.<Cliente>get("cliente")), cliente );
	        predicateList.add(clientePredicate);
	    }
	    
	    if( vendedor!=null ){
	    	Predicate vendedorPredicate = builder.equal( (root.<Vendedor>get("vendedor")), vendedor );
	        predicateList.add(vendedorPredicate);
	    }
	    
	    if( estados != null && estados.size()>0 ){
	    	
	    	Predicate estadoPredicate = root.<EstadoVenta>get("estadoVenta").in(estados);
	    	predicateList.add(estadoPredicate);
	    }

	    if( estadosExcluir != null && estadosExcluir.size()>0 ){
	    	
	    	Predicate estadoPredicate = (root.<EstadoVenta>get("estadoVenta").in(estadosExcluir)).not();
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
		Root<Venta> root = query.from(Venta.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
