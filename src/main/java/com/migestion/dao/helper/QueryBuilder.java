package com.migestion.dao.helper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.migestion.services.criteria.Criteria;


/**
 * Builder para la creación de criterias (jpa) a partir
 * de los criterios de búsqueda.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public abstract class QueryBuilder<T> {

	/**
	 * criteria utilizado para crear el query.
	 */
	protected Criteria criteria;
	
	public QueryBuilder(Criteria criteria){
		this.criteria = criteria;
	}
	
	/**
	 * dado un query se le setean los criterios de búsqueda.
	 * @param query query a setear los criterios.
	 */
	public void enhance( Query query ){
		
		if( criteria.getOffset()!= null )
			query.setFirstResult( criteria.getOffset() );
		
		if( criteria.getMaxResult() != null )
			query.setMaxResults( criteria.getMaxResult() );
		
		
		
		
		
	}
	
	public abstract CriteriaQuery<T> build( EntityManager em );
	
	public abstract CriteriaQuery<Long> buildToCount( EntityManager em );
	
		
}
