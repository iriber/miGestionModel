package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.NotaCredito;
import com.migestion.services.INotaCreditoService;
import com.migestion.services.criteria.NotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para notas de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 */

public class NotaCreditoServiceImpl extends GenericService<NotaCredito, NotaCreditoCriteria> implements INotaCreditoService{

	/**
	 * dao para maejar la persistencia de los notasCredito.
	 */
	private IGenericDAO<NotaCredito, NotaCreditoCriteria> notaCreditoDAO;

	/**
	 * instancia para singleton.
	 */
	private static NotaCreditoServiceImpl instance;
	
	
	public static NotaCreditoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new NotaCreditoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param notaCreditoDAO the notaCreditoDAO to set
	 */
	private NotaCreditoServiceImpl() {
		
		notaCreditoDAO = DAOFactory.getNotaCreditoDAO();
		
	}


	@Override
	protected IGenericDAO<NotaCredito, NotaCreditoCriteria> getDAO() {
		return notaCreditoDAO;
	}


	@Override
	protected void validateOnAdd(NotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(NotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(NotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}