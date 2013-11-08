package com.migestion.services;

import java.util.Date;

import com.migestion.model.Balance;
import com.migestion.model.Sucursal;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de balances
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public interface IBalanceService {
 
	/**
	 * retorna el balance de cajas para una fecha dada
	 * 
	 * Es el balance de todas las cajas
	 * 
	 * @param fecha
	 * @return
	 * @throws ServiceException
	 */
	public Balance getBalanceCajas(Date fecha) throws ServiceException;

	/**
	 * Retorna el balance de cajas para una fecha dada para una sucursal específica.
	 * 
	 * Es el balance de todas las cajas de la sucursal indicada
	 * 
	 * @param fecha
	 * @return
	 * @throws ServiceException
	 */
	public Balance getBalanceCajas(Date fecha, Sucursal sucursal) throws ServiceException;
	
	/**
	 * retorna el balance de bancos para una fecha dada
	 * 
	 * Es el balance de todas cuentas bancarias
	 * 
	 * @param fecha
	 * @return
	 * @throws ServiceException
	 */
	public Balance getBalanceBancos(Date fecha) throws ServiceException;
	
	/**
	 * retorna el balance de cheques para una fecha dada
	 * 
	 * Es el balance de todos los cheques
	 * 
	 * @param fecha
	 * @return
	 * @throws ServiceException
	 */
	public Balance getBalanceCheques(Date fecha) throws ServiceException;

	/**
	 * retorna el balance de notas de crédito para una fecha dada
	 * 
	 * Es el balance de todas las notas de crédito
	 * 
	 * @param fecha
	 * @return
	 * @throws ServiceException
	 */
	public Balance getBalanceNotasCredito(Date fecha) throws ServiceException;
	
}
