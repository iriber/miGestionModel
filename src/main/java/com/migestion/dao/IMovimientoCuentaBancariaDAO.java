package com.migestion.dao;

import com.migestion.model.Balance;
import com.migestion.model.CuentaBancaria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;


/**
 * DAO para movimientos de cuenta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public interface IMovimientoCuentaBancariaDAO extends IMovimientoCuentaDAO{

	/**
	 * balance de la cuenta dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoCuentaCriteria criteria);

	/**
	 * se eliminan los movimientos de la cuenta.
	 * @param cuentaBancaria
	 */
	public void delete(CuentaBancaria cuentaBancaria);
	
}
