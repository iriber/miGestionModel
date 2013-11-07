package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IConceptoMovimientoService;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para conceptos de movimiento.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 */

public class ConceptoMovimientoServiceImpl extends GenericService<ConceptoMovimiento, ConceptoMovimientoCriteria> implements IConceptoMovimientoService{

	/**
	 * dao para manejar la persistencia de las movimientos.
	 */
	private IGenericDAO<ConceptoMovimiento, ConceptoMovimientoCriteria> movimientoDAO;

	/**
	 * instancia para singleton.
	 */
	private static ConceptoMovimientoServiceImpl instance;
	
	
	public static ConceptoMovimientoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new ConceptoMovimientoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param movimientoDAO the sucursalDAO to set
	 */
	private ConceptoMovimientoServiceImpl() {
		
		movimientoDAO = DAOFactory.getConceptoMovimientoDAO();
		
	}


	@Override
	protected IGenericDAO<ConceptoMovimiento, ConceptoMovimientoCriteria> getDAO() {
		return movimientoDAO;
	}


	@Override
	protected void validateOnAdd(ConceptoMovimiento entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(ConceptoMovimiento entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(ConceptoMovimiento entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public ConceptoMovimiento getConceptoVentas() throws ServiceException {
		
		return this.get(ValoresPredefinidos.CONCEPTO_MOVIMIENTO_VENTAS);
	}
	
	public ConceptoMovimiento getConceptoSaldoInicial() throws ServiceException {
		
		return this.get(ValoresPredefinidos.CONCEPTO_MOVIMIENTO_SALDO_INICIAL);
	}

}