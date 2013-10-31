package com.migestion.services;

import java.util.List;

import com.migestion.services.exception.ServiceException;

/**
 * Interface genérica para los servicios de abm
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 * @param <T>
 * @param <TCriteria>
 */
public interface IGenericService<T, TCriteria> {
 
	/**
	 * se agrega una nueva entity
	 * @param entity entity a agregar.
	 * @throws ServiceException
	 */
	void add( T entity ) throws ServiceException;
	
	/**
	 * se modifica una entity
	 * @param entity entity a modificar.
	 * @throws ServiceException
	 */
	void update( T entity ) throws ServiceException;
	
	/**
	 * se elimina una entity
	 * @param oid identificador de la entity a eliminar.
	 * @throws ServiceException
	 */
	void delete( Long oid ) throws ServiceException;
	
	/**
	 * obtiene la entity dado su identificador
	 * @param oid identificador de la entity a buscar.
	 * @return
	 * @throws ServiceException
	 */
	T get( Long oid ) throws ServiceException;


	/**
	 * obtiene el listado de entities dado un criterio de búsqueda.
	 * @param criteria criterio de búsqueda.
	 * @return listado de productos
	 * @throws ServiceException
	 */
	List<T> list( TCriteria criteria ) throws ServiceException;	
	
	/**
	 * obtiene la cantidad de entities dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 * @throws ServiceException
	 */
	Long getListSize(TCriteria criteria) throws ServiceException;
}
