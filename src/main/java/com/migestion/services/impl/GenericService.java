package com.migestion.services.impl;


import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.model.GenericEntity;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.exception.ServiceException;


/**
 * Servicio genérico para MiGestion.
 * 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 */
public abstract class GenericService<T extends GenericEntity, TCriteria extends Criteria>{

	/**
	 * dao para maejar la persistencia de la entity.
	 * @return
	 */
	protected abstract IGenericDAO<T, TCriteria> getDAO();
	
	/**
	 * 
	 * @param criteria
	 * @return
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	public List<T> list(TCriteria criteria)throws ServiceException {

		try {
			
			//obtenemos las entities.
			List<T> entities = getDAO().list(criteria);
			
			return entities;
			
		} catch (DAOException e) {
			
			throw new ServiceException( e );
			
		} catch (Exception e) {
			
			throw new ServiceException( e );
		}
			
	}

	/**
	 * se agrega una entity
	 * @param entity
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	public void add(T entity) throws ServiceException {

		
		try {
			
			//TODO validaciones
			validateOnAdd(entity);
			
			getDAO().add( entity );
			
		} catch (RollbackException e){
			
			throw new ServiceException( e );
			
		} catch (DAOException e) {

			throw new ServiceException( e );
			
		} catch (Exception e) {

			throw new ServiceException( e );
		
		}
	}

	/**
	 * se actualiza una entity
	 * @param entity entity a modificar.
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	public void update(T entity) throws ServiceException {

		try {
			
			//validaciones
			validateOnUpdate(entity);
			
			//persistimos los cambios.
			getDAO().update( entity );
			
		} catch (DAOException e) {

			throw new ServiceException( e );
			
		} catch (Exception e) {

			throw new ServiceException( e );
		
		}
		
	}
	
	/**
	 * se elimina una entity
	 * @param oid identificador de la entity
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	public void delete(Long oid) throws ServiceException {
		
		try {
			
			//validaciones.
			validateOnDelete( get(oid) );
			
			//se elimina la entity.
			getDAO().delete( oid );
			
		} catch (DAOException e) {
			
			throw new ServiceException( e );
			
		} catch (Exception e) {

			throw new ServiceException( e );
		
		}
	}

	/**
	 * se obtiene una entity dado su identificador
	 * @param oid identificador de la entity a buscar
	 * @return
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	public T get(Long oid) throws ServiceException {

		try {

			//obtenemos la entity.
			T entity = (T) getDAO().get( oid );
			
			return entity;
			
		} catch (DAOException e) {
			
			throw new ServiceException( e );
			
		} catch (Exception e) {
			
			throw new ServiceException( e );
		
		}
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 * @throws ServiceException
	 */
	//@Transactional(rollbackFor=ServiceException.class)
	public Long getListSize(TCriteria criteria) throws ServiceException {

		try {
			
			//obtenemos la cantidad de entities.
			Long size = getDAO().getListSize(criteria);
			
			return size;
			
		} catch (DAOException e) {
			
			throw new ServiceException( e );
			
		} catch (Exception e) {
			
			throw new ServiceException( e );
		}
			
	}

	/**
	 * validaciones para agregar la entity
	 * @param entity
	 * @throws ServiceException
	 */
	protected abstract void validateOnAdd(T entity) throws ServiceException;
	
	/**
	 * validaciones para modificar la entity
	 * @param entity
	 * @throws ServiceException
	 */
	protected abstract void validateOnUpdate(T entity) throws ServiceException;
	
	/**
	 * validaciones para eliminar la entity
	 * @param entity
	 * @throws ServiceException
	 */
	protected abstract void validateOnDelete(T entity) throws ServiceException;
}
