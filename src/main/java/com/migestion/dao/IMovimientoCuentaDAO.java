package com.migestion.dao;

import com.migestion.model.Balance;
import com.migestion.services.criteria.MovimientoCuentaCriteria;


/**
 * DAO para movimientos de cuenta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public interface IMovimientoCuentaDAO {

	/**
	 * balance de la cuenta dado un criterio de búsqueda.
	 * @param criteria
	 * @return
	 */
	public Balance getBalance(MovimientoCuentaCriteria criteria);

}
