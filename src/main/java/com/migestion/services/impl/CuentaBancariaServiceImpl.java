package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.CuentaBancaria;
import com.migestion.services.ICuentaBancariaService;
import com.migestion.services.criteria.CuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para cuentas bancarias.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */

public class CuentaBancariaServiceImpl extends GenericService<CuentaBancaria, CuentaBancariaCriteria> implements ICuentaBancariaService{

	/**
	 * dao para maejar la persistencia de los cuentasBancarias.
	 */
	private IGenericDAO<CuentaBancaria, CuentaBancariaCriteria> cuentaBancariaDAO;

	/**
	 * instancia para singleton.
	 */
	private static CuentaBancariaServiceImpl instance;
	
	
	public static CuentaBancariaServiceImpl getInstance(){
		
		if( instance == null )
			instance = new CuentaBancariaServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param cuentaBancariaDAO the cuentaBancariaDAO to set
	 */
	private CuentaBancariaServiceImpl() {
		
		cuentaBancariaDAO = DAOFactory.getCuentaBancariaDAO();
		
	}


	@Override
	protected IGenericDAO<CuentaBancaria, CuentaBancariaCriteria> getDAO() {
		return cuentaBancariaDAO;
	}


	@Override
	protected void validateOnAdd(CuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(CuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(CuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}