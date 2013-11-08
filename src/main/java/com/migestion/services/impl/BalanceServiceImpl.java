package com.migestion.services.impl;

import java.util.Date;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IBalanceDAO;
import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.model.Balance;
import com.migestion.model.Sucursal;
import com.migestion.services.IBalanceService;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Implementación del servicio de balances
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class BalanceServiceImpl implements IBalanceService{

	/**
	 * instancia para singleton.
	 */
	private static BalanceServiceImpl instance;

	private IBalanceDAO balanceDAO;
	
	
	public static BalanceServiceImpl getInstance(){
		
		if( instance == null )
			instance = new BalanceServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param 
	 */
	private BalanceServiceImpl() {
		
		balanceDAO = DAOFactory.getBalanceDAO();
		
	}
	
	/**
	 * dao para maejar la persistencia de la entity.
	 * @return
	 */
	protected IBalanceDAO getDAO(){
	
		return balanceDAO;
	}
	
	
	public Balance getBalanceCajas(Date fecha) throws ServiceException {
		
		IMovimientoCuentaDAO dao = (IMovimientoCuentaDAO) DAOFactory.getMovimientoCajaDAO();
		MovimientoCajaCriteria criteria = new MovimientoCajaCriteria();
		criteria.setFecha(fecha);
		
		Balance balance = dao.getBalance(criteria );
		return balance;
		
	}

	public Balance getBalanceCajas(Date fecha, Sucursal sucursal)
			throws ServiceException {
		
		return getDAO().getBalanceCajas(fecha, sucursal);
	}

	public Balance getBalanceBancos(Date fecha) throws ServiceException {

		IMovimientoCuentaDAO dao = (IMovimientoCuentaDAO) DAOFactory.getMovimientoCuentaBancariaDAO();
		MovimientoCuentaBancariaCriteria criteria = new MovimientoCuentaBancariaCriteria();
		criteria.setFecha(fecha);
		
		Balance balance = dao.getBalance(criteria );
		return balance;
	}

	public Balance getBalanceCheques(Date fecha) throws ServiceException {
		
		IMovimientoCuentaDAO dao = (IMovimientoCuentaDAO) DAOFactory.getMovimientoChequeDAO();
		MovimientoChequeCriteria criteria = new MovimientoChequeCriteria();
		criteria.setFecha(fecha);
		
		Balance balance = dao.getBalance(criteria );
		return balance;
	}

	public Balance getBalanceNotasCredito(Date fecha) throws ServiceException {
		
		IMovimientoCuentaDAO dao = (IMovimientoCuentaDAO) DAOFactory.getMovimientoNotaCreditoDAO();
		MovimientoNotaCreditoCriteria criteria = new MovimientoNotaCreditoCriteria();
		criteria.setFecha(fecha);
		
		Balance balance = dao.getBalance(criteria );
		return balance;
	}

}
