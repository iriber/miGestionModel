package com.migestion.services.impl;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IMovimientoCuentaBancariaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.CuentaBancaria;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.services.ICuentaBancariaService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.CuentaBancariaCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para cuentas bancarias.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */

public class CuentaBancariaServiceImpl extends GenericService<CuentaBancaria, CuentaBancariaCriteria> implements ICuentaBancariaService{

	/**
	 * dao para maejar la persistencia de los cuentasBancarias.
	 */
	private IGenericDAO<CuentaBancaria, CuentaBancariaCriteria> cuentaBancariaDAO;

	/**
	 * instancia para singleton.
	 */
	private static CuentaBancariaServiceImpl instance;
	
	
	public static CuentaBancariaServiceImpl getInstance(){
		
		if( instance == null )
			instance = new CuentaBancariaServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param cuentaBancariaDAO the cuentaBancariaDAO to set
	 */
	private CuentaBancariaServiceImpl() {
		
		cuentaBancariaDAO = DAOFactory.getCuentaBancariaDAO();
		
	}


	@Override
	protected IGenericDAO<CuentaBancaria, CuentaBancariaCriteria> getDAO() {
		return cuentaBancariaDAO;
	}


	@Override
	protected void validateOnAdd(CuentaBancaria entity) throws ServiceException {
		
		//requeridos: nombre, nroCuenta, titular y saldo
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CUENTA_BANCARIA_NOMBRE_REQUERIDO );
		}

		if( StringUtils.isEmpty(entity.getTitular()) ){
			throw new ServiceException( Messages.CUENTA_BANCARIA_TITULAR_REQUERIDO );
		}
		
		if( StringUtils.isEmpty(entity.getNroCuenta()) ){
			throw new ServiceException( Messages.CUENTA_BANCARIA_NRO_CUENTA_REQUERIDO );
		}
		
		
		if(entity.getSaldoInicial()==null)
			entity.setSaldoInicial(0F);
		
		//nombre único
		CuentaBancariaCriteria criteria = new CuentaBancariaCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otra cuenta con el mismo nombre, exigimos que ingrese
			//el nro de cuenta
			if( entity.getNroCuenta()==null ){
		
				throw new ServiceException( Messages.CUENTA_BANCARIA_NOMBRE_REPETIDO_NRO_CUENTA_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el nroCuenta
				criteria.setNroCuenta( entity.getNroCuenta() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.CUENTA_BANCARIA_DUPLICADA );
			}	
			
		}
	}

	@Override
	protected void validateOnUpdate(CuentaBancaria entity) throws ServiceException {

		validateOnAdd(entity);
	}

	@Override
	protected void validateOnDelete(CuentaBancaria entity) throws ServiceException {
		
		//que no tenga dependencias.

		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.CUENTA_BANCARIA_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(CuentaBancaria entity) throws ServiceException {

		
		entity.setSaldo(0F);
		
		// agregamos la cuenta bancaria.
		super.add(entity);
		
		//agregamos un movimiento por el saldo inicial
		if(entity.getSaldoInicial()>0){
			MovimientoCuentaBancaria movimiento = new MovimientoCuentaBancaria();
			movimiento.setCuentaBancaria(entity);
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoSaldoInicial() );
			movimiento.setHaber( entity.getSaldoInicial() );
			movimiento.setFechaHora( new Date() );

			ServiceFactory.getMovimientoCuentaBancariaService().add(movimiento);
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 */
	public void delete(Long oid) throws ServiceException {

		try {
			
			CuentaBancaria cuenta = get(oid);
			
			//validaciones.
			validateOnDelete( cuenta );
			
			//eliminamos los movimientos de la cuenta bancaria.
			((IMovimientoCuentaBancariaDAO)DAOFactory.getMovimientoCuentaBancariaDAO()).delete( cuenta  );
			
			//se elimina la entity.
			getDAO().delete( oid );
			
		} catch (DAOException e) {
			
			throw new ServiceException( e );
			
		} catch (Exception e) {

			throw new ServiceException( e );
		
		}

		
		
	}	
}