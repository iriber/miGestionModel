package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGastoDAO;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.model.EstadisticaGasto;
import com.migestion.model.Gasto;
import com.migestion.model.MovimientoCuenta;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IGastoService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.GastoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para gastos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 */

public class GastoServiceImpl extends GenericService<Gasto, GastoCriteria> implements IGastoService{

	/**
	 * dao para maejar la persistencia de los gastos.
	 */
	private IGenericDAO<Gasto, GastoCriteria> gastoDAO;

	/**
	 * instancia para singleton.
	 */
	private static GastoServiceImpl instance;
	
	
	public static GastoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new GastoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param gastoDAO the gastoDAO to set
	 */
	private GastoServiceImpl() {
		
		gastoDAO = DAOFactory.getGastoDAO();
		
	}


	@Override
	protected IGenericDAO<Gasto, GastoCriteria> getDAO() {
		return gastoDAO;
	}


	@Override
	protected void validateOnAdd(Gasto entity) throws ServiceException {
		
		//requeridos: fecha, monto, iva, retencionIIBB, tipoFactura, concepto
		//sucursal
		if( entity.getFecha() == null){
			throw new ServiceException( Messages.GASTO_FECHA_REQUERIDA );
		}
		
		if( entity.getMonto() == null || entity.getMonto() <= 0){
			throw new ServiceException( Messages.GASTO_MONTO_REQUERIDO );
		}
		
		if( entity.getIva() == null || entity.getIva() <= 0){
			throw new ServiceException( Messages.GASTO_IVA_REQUERIDO );
		}
		
		if( entity.getRentencionIIBB() == null ){
			throw new ServiceException( Messages.GASTO_RETENCION_IIBB_REQUERIDA );
		}
		
		if( entity.getTipoFactura() == null){
			throw new ServiceException( Messages.GASTO_TIPO_FACTURA_REQUERIDO );
		}
		
		if( entity.getSucursal() == null){
			throw new ServiceException( Messages.GASTO_SUCURSAL_REQUERIDA );
		}
		
		if( entity.getMovimiento().getConcepto() == null){
			throw new ServiceException( Messages.GASTO_CONCEPTO_REQUERIDO );
		}
		
		
	}

	@Override
	protected void validateOnUpdate(Gasto entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Gasto entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Gasto entity) throws ServiceException {

		//hay que actualizar el saldo de las cuenta afectada (forma pago).
		entity.getMovimiento().calcularSaldos();
		
		//le seteamos una descripción al movimiento
		String descripcion = entity.getMovimiento().getDescripcion();
		if( StringUtils.isEmpty( descripcion) ){
			
			descripcion = entity.getObservaciones();
			
		}
		entity.getMovimiento().setDescripcion(descripcion);
		
		
		//TODO movimiento en cuenta IVA y cuenta IIBB??
		
		// agregamos el gasto.
		super.add(entity);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IGastoService#getEstadisticaGasto(com.migestion.services.criteria.GastoCriteria)
	 */
	public EstadisticaGasto getEstadisticaGasto(GastoCriteria criteria)
			throws ServiceException {

		return ((IGastoDAO) getDAO()).getEstadisticaGasto(criteria);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 */
	public void delete(Long oid) throws ServiceException {

		Gasto gasto = this.get(oid);


		try {
			
			//hay que generar el contra movimiento para corregir el saldo de la cuenta asociada
			ConceptoMovimiento concepto= ServiceFactory.getConceptoMovimientoService().get( ValoresPredefinidos.CONCEPTO_MOVIMIENTO_ANULACION_GASTO );
			
			MovimientoCuenta movimiento = gasto.buildContraMovimiento(concepto);
			movimiento.calcularSaldos();
			movimiento.setDescripcion("Gasto #" + oid);
			DAOFactory.getMovimientoCuentaDAO().add(movimiento);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		super.delete(oid);

	}

}