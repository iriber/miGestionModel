package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.Cheque;
import com.migestion.services.IChequeService;
import com.migestion.services.criteria.ChequeCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para cheques.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */

public class ChequeServiceImpl extends GenericService<Cheque, ChequeCriteria> implements IChequeService{

	/**
	 * dao para maejar la persistencia de los cheques.
	 */
	private IGenericDAO<Cheque, ChequeCriteria> chequeDAO;

	/**
	 * instancia para singleton.
	 */
	private static ChequeServiceImpl instance;
	
	
	public static ChequeServiceImpl getInstance(){
		
		if( instance == null )
			instance = new ChequeServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param chequeDAO the chequeDAO to set
	 */
	private ChequeServiceImpl() {
		
		chequeDAO = DAOFactory.getChequeDAO();
		
	}


	@Override
	protected IGenericDAO<Cheque, ChequeCriteria> getDAO() {
		return chequeDAO;
	}


	@Override
	protected void validateOnAdd(Cheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(Cheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Cheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}