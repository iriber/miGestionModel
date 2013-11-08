package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCheque;
import com.migestion.services.IMovimientoChequeService;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de cheques
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */

public class MovimientoChequeServiceImpl extends GenericService<MovimientoCheque, MovimientoChequeCriteria> implements IMovimientoChequeService{

	/**
	 * dao para manejar la persistencia de las movimientos de cheque.
	 */
	private IGenericDAO<MovimientoCheque, MovimientoChequeCriteria> movimientoChequeDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoChequeServiceImpl instance;
	
	
	public static MovimientoChequeServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoChequeServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoChequeDAO the movimientoChequeDAO to set
	 */
	private MovimientoChequeServiceImpl() {
		
		movimientoChequeDAO = DAOFactory.getMovimientoChequeDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoCheque, MovimientoChequeCriteria> getDAO() {
		return movimientoChequeDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoCheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoCheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoCheque entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoCheque entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual de la cheque
		entity.calcularSaldos();
		
		// agregamos el movimimiento de cheque.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoChequeService#getEstadisticaCheque(com.migestion.services.criteria.MovimientoChequeCriteria)
	 */
	public Balance getBalance(MovimientoChequeCriteria criteria)
			throws ServiceException {

		Balance balance = ((IMovimientoCuentaDAO) getDAO()).getBalance(criteria);

		balance.setSaldoInicial(0F);
		
		return balance;
	}

}