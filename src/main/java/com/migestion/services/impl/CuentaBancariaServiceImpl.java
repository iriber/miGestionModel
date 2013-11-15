package com.migestion.services.impl;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
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
		
		//requeridos: nombre, precio, iva
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CUENTA_BANCARIA_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		CuentaBancariaCriteria criteria = new CuentaBancariaCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		if( getListSize(criteria) > 0 ){
			throw new ServiceException( Messages.CUENTA_BANCARIA_NOMBRE_REPETIDO );
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
		MovimientoCuentaBancaria movimiento = new MovimientoCuentaBancaria();
		movimiento.setCuentaBancaria(entity);
		movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoSaldoInicial() );
		movimiento.setHaber( entity.getSaldoInicial() );
		movimiento.setFechaHora( new Date() );

		ServiceFactory.getMovimientoCuentaBancariaService().add(movimiento);
		
		
	}

}