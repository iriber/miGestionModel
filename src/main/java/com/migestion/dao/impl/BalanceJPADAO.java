package com.migestion.dao.impl;


import java.util.Date;

import javax.persistence.EntityManager;

import com.migestion.dao.IBalanceDAO;
import com.migestion.dao.PersistenceContext;
import com.migestion.model.Balance;
import com.migestion.model.Sucursal;
import com.migestion.services.exception.ServiceException;

/**
 * Implementación del DAO para balances
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 * 
 */
public class BalanceJPADAO implements IBalanceDAO {

	/**
	 * entity manager
	 */
	private EntityManager entityManager = PersistenceContext.getInstance().getEntityManager();

	//@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		
		if( !this.entityManager.isOpen() )
			this.entityManager = PersistenceContext.getInstance().getEntityManager();
		return this.entityManager;
	}

	public Balance getBalanceCajas(Date fecha) throws ServiceException {
		
				
		
		return null;
	}

	public Balance getBalanceCajas(Date fecha, Sucursal sucursal)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Balance getBalanceBancos(Date fecha) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Balance getBalanceCheques(Date fecha) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


}
