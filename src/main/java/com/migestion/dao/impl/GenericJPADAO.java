package com.migestion.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.migestion.dao.IGenericDAO;
import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.GenericEntity;
import com.migestion.services.criteria.Criteria;

/**
 * Implementación del DAO genérico para las entities de MiGestion
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 * @param <T>
 */
public abstract class GenericJPADAO<T extends GenericEntity, TCriteria extends Criteria> implements
		IGenericDAO<T, TCriteria> {

	/**
	 * entity manager
	 */
	private EntityManager entityManager = PersistenceContext.getInstance().getEntityManager();

	//@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * helper para la construcción de queries.
	 * 
	 * @param criteria
	 * @return
	 */
	protected abstract QueryBuilder<T> getQueryBuilder(Criteria criteria);

	/**
	 * arma un query dado un criterio de búsqueda.
	 * 
	 * @param criteria
	 * @return
	 */
	protected Query getQuery(Criteria criteria) {

		// delegamos al helper para que forme el query dado el criteria.
		QueryBuilder<T> helper = getQueryBuilder(criteria);

		// creamos el query.
		Query query = getEntityManager().createQuery(
				helper.build(getEntityManager()));

		helper.enhance(query);

		return query;
	}

	protected Query getQuerySize(Criteria criteria) {

		//le quitamos la info de paginación
		Integer max = ((Criteria)criteria).getMaxResult();
		Integer offset = ((Criteria)criteria).getOffset();
		((Criteria)criteria).setMaxResult( null );
		((Criteria)criteria).setOffset( null );
		
		// delegamos al helper para que forme el query dado el criteria.
		QueryBuilder<T> helper = getQueryBuilder(criteria);
		 
		// creamos el query.
		Query query = getEntityManager().createQuery(
				helper.buildToCount(getEntityManager()));

		helper.enhance(query);

		//le volvemos a incorporar la info de paginación
		((Criteria)criteria).setMaxResult( max );
		((Criteria)criteria).setOffset( offset );
		return query;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#list(java.lang.Object)
	 */	
	public List<T> list(TCriteria criteria) throws DAOException {

		Query q = getQuery(criteria);

		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) q.getResultList();

		return entities;
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#getListSize(java.lang.Object)
	 */
	public Long getListSize(TCriteria criteria) throws DAOException{
		Long size = 0L;
		
		Query q = getQuerySize(criteria);
		size = (Long)q.getSingleResult();
		return size;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#add(java.lang.Object)
	 */
	public void add(T entity) throws DAOException {
		
		entityManager.persist(entity);

	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#update(java.lang.Object)
	 */
	public void update(T entity) throws DAOException {

		entityManager.merge(entity);

	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#delete(java.lang.Long)
	 */
	public void delete(Long oid) throws DAOException {

		entityManager.remove(get(oid));

	}

}
