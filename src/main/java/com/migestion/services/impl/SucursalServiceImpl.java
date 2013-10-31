package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.Sucursal;
import com.migestion.services.ISucursalService;
import com.migestion.services.criteria.SucursalCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para sucursales.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */

public class SucursalServiceImpl extends GenericService<Sucursal, SucursalCriteria> implements ISucursalService{

	/**
	 * dao para manejar la persistencia de las sucursales.
	 */
	private IGenericDAO<Sucursal, SucursalCriteria> sucursalDAO;

	/**
	 * instancia para singleton.
	 */
	private static SucursalServiceImpl instance;
	
	
	public static SucursalServiceImpl getInstance(){
		
		if( instance == null )
			instance = new SucursalServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param sucursalDAO the sucursalDAO to set
	 */
	private SucursalServiceImpl() {
		
		sucursalDAO = DAOFactory.getSucursalDAO();
		
	}


	@Override
	protected IGenericDAO<Sucursal, SucursalCriteria> getDAO() {
		return sucursalDAO;
	}


	@Override
	protected void validateOnAdd(Sucursal entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(Sucursal entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Sucursal entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}