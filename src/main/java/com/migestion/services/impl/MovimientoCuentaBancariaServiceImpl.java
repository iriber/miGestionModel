package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.IMovimientoCuentaBancariaService;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de cuentas bancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 */

public class MovimientoCuentaBancariaServiceImpl extends GenericService<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria> implements IMovimientoCuentaBancariaService{

	/**
	 * dao para manejar la persistencia de las movimientos de cuentaBancaria.
	 */
	private IGenericDAO<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria> movimientoCuentaBancariaDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoCuentaBancariaServiceImpl instance;
	
	
	public static MovimientoCuentaBancariaServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoCuentaBancariaServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoCuentaBancariaDAO the movimientoCuentaBancariaDAO to set
	 */
	private MovimientoCuentaBancariaServiceImpl() {
		
		movimientoCuentaBancariaDAO = DAOFactory.getMovimientoCuentaBancariaDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria> getDAO() {
		return movimientoCuentaBancariaDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoCuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoCuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoCuentaBancaria entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoCuentaBancaria entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual de la cuentaBancaria
		entity.calcularSaldos();
		
		// agregamos el movimimiento de cuentaBancaria.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoCuentaBancariaService#getEstadisticaCuentaBancaria(com.migestion.services.criteria.MovimientoCuentaBancariaCriteria)
	 */
	public Balance getBalance(MovimientoCuentaBancariaCriteria criteria)
			throws ServiceException {

		Balance balance = ((IMovimientoCuentaDAO) getDAO()).getBalance(criteria);
		
		//tengo que setearle el saldo inicial.
		
		if( criteria.getCuentaBancaria() != null ){
			
			balance.setSaldoInicial( criteria.getCuentaBancaria().getSaldoInicial() );
		}
		
		return balance;
	}

}