package com.migestion.dao;

import java.util.List;

import com.migestion.dao.exception.DAOException;

/**
 * Interfacde genérica para los DAOs.
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 * @param <T>
 * @param <TCriteria>
 */
public interface IGenericDAO<T, TCriteria> {

	List<T> list(TCriteria criteria) throws DAOException;
	
	void add( T entity ) throws DAOException;
	
	void update( T entity ) throws DAOException;
	
	void delete( Long oid ) throws DAOException;
	
	T get( Long oid ) throws DAOException;
	
	Long getListSize(TCriteria criteria) throws DAOException;
	
	Boolean hasDependencies(T entity) throws DAOException;
}
