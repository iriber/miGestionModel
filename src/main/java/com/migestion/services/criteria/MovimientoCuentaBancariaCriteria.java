package com.migestion.services.criteria;

import com.migestion.model.CuentaBancaria;

/**
 * Criterio de búsqueda para movimientos de cuentas bancarias.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoCuentaBancariaCriteria extends MovimientoCuentaCriteria{

	private CuentaBancaria cuentaBancaria;

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	
}