package com.migestion.services.criteria;

import com.migestion.model.Cliente;

/**
 * Criterio de búsqueda para movimientos de cuentas 
 * corrientes de clientes.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
public class MovimientoCuentaClienteCriteria extends MovimientoCuentaCriteria{

	private Cliente cliente ;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}