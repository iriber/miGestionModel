package com.migestion.services;

import com.migestion.model.ConceptoMovimiento;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de conceptos de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public interface IConceptoMovimientoService extends IGenericService<ConceptoMovimiento, ConceptoMovimientoCriteria>{
 

	/**
	 * obtiene el concepto de caja utilizado para ventas.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoVentas() throws ServiceException;
	
	/**
	 * obtiene el concepto de caja utilizado para saldo inicial.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoSaldoInicial() throws ServiceException;
}
