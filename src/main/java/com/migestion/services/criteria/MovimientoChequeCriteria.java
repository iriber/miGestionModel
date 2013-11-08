package com.migestion.services.criteria;

import com.migestion.model.Cheque;

/**
 * Criterio de búsqueda para movimientos de cheques.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoChequeCriteria extends MovimientoCuentaCriteria{

	private Cheque cheque;

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	
}