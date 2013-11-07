package com.migestion.dao.impl;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IBalanceDAO;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.dao.helper.VentaQueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.EstadisticaVenta;
import com.migestion.model.GenericEntity;
import com.migestion.model.Sucursal;
import com.migestion.model.Venta;
import com.migestion.services.criteria.Criteria;
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
