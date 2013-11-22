package com.migestion.services.criteria;

import com.migestion.model.Cliente;

/**
 * Criterio de búsqueda para pagos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class PagoClienteCriteria extends PagoCriteria{

	/**
	 * cliente
	 */
	private Cliente cliente;
	
	
	public PagoClienteCriteria(){
		
		super();
	}


	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}