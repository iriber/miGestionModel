package com.migestion.services.impl;

import java.util.Date;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.ICajaDAO;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.model.Caja;
import com.migestion.model.EstadisticaCaja;
import com.migestion.model.EstadoCaja;
import com.migestion.model.MovimientoCaja;
import com.migestion.services.ICajaService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Implementación del servicio para cajas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */

public class CajaServiceImpl extends GenericService<Caja, CajaCriteria>
		implements ICajaService {

	/**
	 * dao para maejar la persistencia de los cajas.
	 */
	private IGenericDAO<Caja, CajaCriteria> cajaDAO;

	/**
	 * instancia para singleton.
	 */
	private static CajaServiceImpl instance;

	public static CajaServiceImpl getInstance() {

		if (instance == null)
			instance = new CajaServiceImpl();

		return instance;
	}

	/**
	 * @param cajaDAO
	 *            the cajaDAO to set
	 */
	private CajaServiceImpl() {

		cajaDAO = DAOFactory.getCajaDAO();

	}

	@Override
	protected IGenericDAO<Caja, CajaCriteria> getDAO() {
		return cajaDAO;
	}

	@Override
	protected void validateOnAdd(Caja entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateOnUpdate(Caja entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateOnDelete(Caja entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(Caja entity) throws ServiceException {


		// la seteamos como abierta.
		entity.setEstadoCaja(EstadoCaja.ABIERTA);

		//seteamos el saldo como el saldo inicial
		//entity.setSaldo( entity.getSaldoInicial() );
		
		entity.setSaldo(0F);
		
		// agregamos la caja.
		super.add(entity);
		
		//agregamos un movimiento por el saldo inicial
		MovimientoCaja movimiento = new MovimientoCaja();
		movimiento.setCaja(entity);
		movimiento.setConcepto( ServiceFactory.getConceptoCajaService().getConceptoSaldoInicial() );
		movimiento.setHaber( entity.getSaldoInicial() );
		movimiento.setFechaHora( entity.getFecha() );

		ServiceFactory.getMovimientoCajaService().add(movimiento);
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void update(Caja entity) throws ServiceException {

		//TODO
		//super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.ICajaService#getEstadisticaCaja(com.migestion.services.criteria.CajaCriteria)
	 */
	public EstadisticaCaja getEstadisticaCaja(CajaCriteria criteria)
			throws ServiceException {

		return ((ICajaDAO) getDAO()).getEstadisticaCaja(criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.ICajaService#cerrarCaja(java.lang.Long)
	 */
	public void cerrarCaja(Long oid) throws ServiceException {

		Caja caja = this.get(oid);

		if( !caja.getEstadoCaja().podesCerrarte() )
			throw new ServiceException("caja.cerrar.estado.invalido");
		
		caja.setEstadoCaja(EstadoCaja.CERRADA);

		try {

			getDAO().update(caja);

		} catch (DAOException e) {

			throw new ServiceException(e);

		} catch (Exception e) {

			throw new ServiceException(e);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 */
	public void delete(Long oid) throws ServiceException {

		Caja caja = this.get(oid);

		super.delete(oid);

	}

}