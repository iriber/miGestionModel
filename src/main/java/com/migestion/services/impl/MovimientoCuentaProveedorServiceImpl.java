package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.services.IMovimientoCuentaProveedorService;
import com.migestion.services.criteria.MovimientoCuentaProveedorCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de cuentas
 * corrientes de proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 */

public class MovimientoCuentaProveedorServiceImpl extends GenericService<MovimientoCuentaProveedor, MovimientoCuentaProveedorCriteria> implements IMovimientoCuentaProveedorService{

	/**
	 * dao para manejar la persistencia de las movimientos de cuentaProveedor.
	 */
	private IGenericDAO<MovimientoCuentaProveedor, MovimientoCuentaProveedorCriteria> movimientoCuentaProveedorDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoCuentaProveedorServiceImpl instance;
	
	
	public static MovimientoCuentaProveedorServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoCuentaProveedorServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoCuentaProveedorDAO the movimientoCuentaProveedorDAO to set
	 */
	private MovimientoCuentaProveedorServiceImpl() {
		
		movimientoCuentaProveedorDAO = DAOFactory.getMovimientoCuentaProveedorDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoCuentaProveedor, MovimientoCuentaProveedorCriteria> getDAO() {
		return movimientoCuentaProveedorDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoCuentaProveedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoCuentaProveedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoCuentaProveedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoCuentaProveedor entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual del proveedor
		entity.calcularSaldos();
		
		// agregamos el movimimiento de cuentaProveedor.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoCuentaProveedorService#getBalance(com.migestion.services.criteria.MovimientoCuentaProveedorCriteria)
	 */
	public Balance getBalance(MovimientoCuentaProveedorCriteria criteria)
			throws ServiceException {

		Balance balance = ((IMovimientoCuentaDAO) getDAO()).getBalance(criteria);
		
		return balance;
	}

}