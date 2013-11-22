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

import com.migestion.model.EstadoPago;
import com.migestion.model.Operacion;
import com.migestion.model.PagoProveedor;
import com.migestion.model.Proveedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.criteria.PagoProveedorCriteria;


/**
 * Helper que colabora en la creación de queries para
 * los pagos a proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class PagoProveedorQueryBuilder extends QueryBuilder<PagoProveedor>{

	
	public PagoProveedorQueryBuilder(Criteria criteria){
		super( criteria );
	}
	
	@Override
	public CriteriaQuery<PagoProveedor> build( EntityManager em) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<PagoProveedor> query = builder.createQuery(PagoProveedor.class);
	    Root<PagoProveedor> root = query.from(PagoProveedor.class);
	    query.select(root);
	 	    
	    query.where(getPredicates(root, builder, criteria));		
		
	    query.orderBy( criteria.buildOrderBy( root, builder ));
	    
		return query;

	}

	public Predicate[] getPredicates(Root<PagoProveedor> root,
			CriteriaBuilder builder, Criteria criteria) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		 
		Date fechaDesde = ((PagoCriteria)criteria).getFechaDesde();
		Date fechaHasta = ((PagoCriteria)criteria).getFechaHasta();
		Proveedor cliente = ((PagoProveedorCriteria)criteria).getProveedor();
		Operacion operacion = ((PagoCriteria)criteria).getOperacion();
		Collection<EstadoPago> estados = ((PagoCriteria)criteria).getEstados();
		Collection<EstadoPago> estadosExcluir = ((PagoCriteria)criteria).getEstadosExcluir();
	 
	    if( fechaDesde!=null ){
	    	Predicate desdePredicate = builder.greaterThanOrEqualTo( (root.<Date>get("fecha")), fechaDesde );
	        predicateList.add(desdePredicate);
	    }
	    if( fechaHasta!=null ){
	    	Predicate hastaPredicate = builder.lessThanOrEqualTo( (root.<Date>get("fecha")), fechaHasta );
	        predicateList.add(hastaPredicate);
	    }
	    
	    if( cliente!=null ){
	    	Predicate clientePredicate = builder.equal( (root.<Proveedor>get("cliente")), cliente );
	        predicateList.add(clientePredicate);
	    }

	    
	    if( operacion!=null ){
	    	
	    	Predicate operacionPredicate = builder.equal(root.join("detallesPago").get("operacion"), operacion);
	        predicateList.add(operacionPredicate);
	    }

	    if( estados != null && estados.size()>0 ){
	    	
	    	Predicate estadoPredicate = root.<EstadoPago>get("estadoPago").in(estados);
	    	predicateList.add(estadoPredicate);
	    }

	    if( estadosExcluir != null && estadosExcluir.size()>0 ){
	    	
	    	Predicate estadoPredicate = (root.<EstadoPago>get("estadoPago").in(estadosExcluir)).not();
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
		Root<PagoProveedor> root = query.from(PagoProveedor.class);
		
		query.select( builder.count( root.<Long>get( "oid" ) ) );

		query.where(getPredicates(root, builder, criteria));

		return query;
	}
}
