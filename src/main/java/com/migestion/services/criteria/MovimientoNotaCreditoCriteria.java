package com.migestion.services.criteria;

import com.migestion.model.NotaCredito;

/**
 * Criterio de búsqueda para movimientos de cheques.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class MovimientoNotaCreditoCriteria extends MovimientoCuentaCriteria{

	private NotaCredito notaCredito;

	public NotaCredito getNotaCredito() {
		return notaCredito;
	}

	public void setNotaCredito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
	}

	
}