package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.services.IMovimientoCuentaClienteService;
import com.migestion.services.criteria.MovimientoCuentaClienteCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de cuentas
 * corrientes de clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 */

public class MovimientoCuentaClienteServiceImpl extends GenericService<MovimientoCuentaCliente, MovimientoCuentaClienteCriteria> implements IMovimientoCuentaClienteService{

	/**
	 * dao para manejar la persistencia de las movimientos de cuentaCliente.
	 */
	private IGenericDAO<MovimientoCuentaCliente, MovimientoCuentaClienteCriteria> movimientoCuentaClienteDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoCuentaClienteServiceImpl instance;
	
	
	public static MovimientoCuentaClienteServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoCuentaClienteServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoCuentaClienteDAO the movimientoCuentaClienteDAO to set
	 */
	private MovimientoCuentaClienteServiceImpl() {
		
		movimientoCuentaClienteDAO = DAOFactory.getMovimientoCuentaClienteDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoCuentaCliente, MovimientoCuentaClienteCriteria> getDAO() {
		return movimientoCuentaClienteDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoCuentaCliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoCuentaCliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoCuentaCliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoCuentaCliente entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual del cliente
		entity.calcularSaldos();
		
		// agregamos el movimimiento de cuentaCliente.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoCuentaClienteService#getBalance(com.migestion.services.criteria.MovimientoCuentaClienteCriteria)
	 */
	public Balance getBalance(MovimientoCuentaClienteCriteria criteria)
			throws ServiceException {

		Balance balance = ((IMovimientoCuentaDAO) getDAO()).getBalance(criteria);
		
		return balance;
	}

}