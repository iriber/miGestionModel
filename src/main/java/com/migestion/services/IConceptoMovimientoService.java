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
	 * obtiene el concepto de movimiento utilizado para ventas.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoVentas() throws ServiceException;
	
	/**
	 * obtiene el concepto de movimiento utilizado para anulación de ventas.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoAnulacionVenta() throws ServiceException;
	
	/**
	 * obtiene el concepto de movimiento utilizado para saldo inicial.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoSaldoInicial() throws ServiceException;
	
	/**
	 * obtiene el concepto de movimiento utilizado para los pagos de ventas.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoPagoVenta() throws ServiceException;

	/**
	 * obtiene el concepto de movimiento utilizado para anulación de gastos.
	 * @return
	 * @throws ServiceException
	 */
	public ConceptoMovimiento getConceptoAnulacionGasto() throws ServiceException;
	
}
