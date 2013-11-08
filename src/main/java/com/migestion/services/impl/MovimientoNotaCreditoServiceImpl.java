package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoNotaCredito;
import com.migestion.services.IMovimientoNotaCreditoService;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de notas de crédito
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */

public class MovimientoNotaCreditoServiceImpl extends GenericService<MovimientoNotaCredito, MovimientoNotaCreditoCriteria> implements IMovimientoNotaCreditoService{

	/**
	 * dao para manejar la persistencia de las movimientos de notaCredito.
	 */
	private IGenericDAO<MovimientoNotaCredito, MovimientoNotaCreditoCriteria> movimientoNotaCreditoDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoNotaCreditoServiceImpl instance;
	
	
	public static MovimientoNotaCreditoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoNotaCreditoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoNotaCreditoDAO the movimientoNotaCreditoDAO to set
	 */
	private MovimientoNotaCreditoServiceImpl() {
		
		movimientoNotaCreditoDAO = DAOFactory.getMovimientoNotaCreditoDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoNotaCredito, MovimientoNotaCreditoCriteria> getDAO() {
		return movimientoNotaCreditoDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoNotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoNotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoNotaCredito entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoNotaCredito entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual de la notaCredito
		entity.calcularSaldos();
		
		// agregamos el movimimiento de notaCredito.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoNotaCreditoService#getEstadisticaNotaCredito(com.migestion.services.criteria.MovimientoNotaCreditoCriteria)
	 */
	public Balance getBalance(MovimientoNotaCreditoCriteria criteria)
			throws ServiceException {

		Balance balance = ((IMovimientoCuentaDAO) getDAO()).getBalance(criteria);

		balance.setSaldoInicial(0F);
		
		return balance;
	}

}