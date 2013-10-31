package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.ICajaDAO;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCajaDAO;
import com.migestion.model.Caja;
import com.migestion.model.EstadisticaCaja;
import com.migestion.model.EstadoCaja;
import com.migestion.model.MovimientoCaja;
import com.migestion.services.IMovimientoCajaService;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para movimientos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */

public class MovimientoCajaServiceImpl extends GenericService<MovimientoCaja, MovimientoCajaCriteria> implements IMovimientoCajaService{

	/**
	 * dao para manejar la persistencia de las movimientos de caja.
	 */
	private IGenericDAO<MovimientoCaja, MovimientoCajaCriteria> movimientoCajaDAO;

	/**
	 * instancia para singleton.
	 */
	private static MovimientoCajaServiceImpl instance;
	
	
	public static MovimientoCajaServiceImpl getInstance(){
		
		if( instance == null )
			instance = new MovimientoCajaServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoCajaDAO the movimientoCajaDAO to set
	 */
	private MovimientoCajaServiceImpl() {
		
		movimientoCajaDAO = DAOFactory.getMovimientoCajaDAO();
		
	}


	@Override
	protected IGenericDAO<MovimientoCaja, MovimientoCajaCriteria> getDAO() {
		return movimientoCajaDAO;
	}


	@Override
	protected void validateOnAdd(MovimientoCaja entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(MovimientoCaja entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(MovimientoCaja entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(MovimientoCaja entity) throws ServiceException {


		//calculamos el saldo en base al movimiento y el saldo actual de la caja
		entity.calcularSaldos();
		
		// agregamos el movimimiento de caja.
		super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IMovimientoCajaService#getEstadisticaCaja(com.migestion.services.criteria.MovimientoCajaCriteria)
	 */
	public EstadisticaCaja getEstadisticaCaja(MovimientoCajaCriteria criteria)
			throws ServiceException {

		EstadisticaCaja estadistica = ((IMovimientoCajaDAO) getDAO()).getEstadisticaCaja(criteria);
		
		//tengo que setearle el saldo inicial.
		
		if( criteria.getCaja() != null ){
			
			estadistica.setSaldoInicial( criteria.getCaja().getSaldoInicial() );
		}
		
		return estadistica;
	}

}