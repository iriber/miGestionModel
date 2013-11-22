package com.migestion.services.impl;


import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.DetalleFormaPago;
import com.migestion.model.DetallePago;
import com.migestion.model.EstadisticaPago;
import com.migestion.model.EstadoPago;
import com.migestion.model.Pago;
import com.migestion.services.IPagoService;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para pagos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */

abstract public class PagoServiceImpl<T extends Pago, TCriteria extends PagoCriteria> extends GenericService<T, TCriteria> implements IPagoService<T, TCriteria>{

	/**
	 * dao para maejar la persistencia de los pagos.
	 */
	private IGenericDAO<T, TCriteria> pagoDAO;

	
	/**
	 * @param pagoDAO the pagoDAO to set
	 */
	protected PagoServiceImpl() {
		
		pagoDAO = buildDAO();
		
	}

	protected abstract IGenericDAO<T, TCriteria> buildDAO();
	

	@Override
	protected IGenericDAO<T, TCriteria> getDAO() {
		return pagoDAO;
	}


	@Override
	protected void validateOnAdd(T entity) throws ServiceException {
		
		//requeridos: fecha, cliente, sucursal, ventas a pagar 
		if( entity.getFecha() == null ){
			throw new ServiceException( Messages.PAGO_FECHA_REQUERIDA );
		}
		
		if( entity.getDetallesPago().size() == 0 ){
			throw new ServiceException( Messages.PAGO_VENTAS_REQUERIDAS );
		}

		if( entity.getDetallesFormaPago().size() == 0 ){
			throw new ServiceException( Messages.PAGO_FORMAS_PAGO_REQUERIDAS );
		}

		//validar los detalles.
		
		//que no haya ventas anuladas ni pagadas
		
		//TODO que la suma de los detalles sea igual al monto del pago.
		Float totalDetalles = 0F;
		for (DetallePago detalle : entity.getDetallesPago()) {
			
			totalDetalles += detalle.getMonto();
			
//			if( !detalle.getOperacion().podesPagarte() )
//				throw new ServiceException( Messages.PAGO_VENTAS_INCORRECTAS );	
			
		}
		
		//TODO que no pague más de lo que debe
		

	}

	@Override
	protected void validateOnUpdate(T entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(T entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(T entity) throws ServiceException {

		// lo seteamos como realizado.
		entity.setEstadoPago(EstadoPago.REALIZADO);

		//hay que actualizar los saldos de las cuentas afectadas (detalles forma pago).
		for (DetalleFormaPago detalle : entity.getDetallesFormaPago()) {
			detalle.getMovimiento().calcularSaldos();
		}
	
		updateCuentaCorrienteOnAdd( entity );
		
		
//		if( entity.getCliente().getTieneCtaCte() ){
//
//			//haber sobre la cuenta corriente del cliente.
//			MovimientoCuentaCliente movimiento = new MovimientoCuentaCliente();
//			movimiento.setCliente( entity.getCliente() );
//			movimiento.setHaber( entity.getMonto() );
//			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoPagoVenta() );
//			movimiento.setFechaHora( new Date() );
//			movimiento.setDescripcion("Pago # " + entity.getOid() );
//			ServiceFactory.getMovimientoCuentaClienteService().add(movimiento);
//			
//		}
		// agregamos el pago.
		super.add(entity);
	}

	
	protected abstract void updateCuentaCorrienteOnAdd(T pago) throws ServiceException;


	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IPagoService#getEstadisticaPago(com.migestion.services.criteria.PagoCriteria)
	 */
	public EstadisticaPago getEstadisticaPago(TCriteria criteria)
			throws ServiceException {

		//excluimos las ventas anuladas
		criteria.addEstadoExcluir( EstadoPago.ANULADO );
		
		return ((IPagoDAO) getDAO()).getEstadisticaPago(criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IPagoService#anularPago(java.lang.Long)
	 */
	public T anularPago(Long oid) throws ServiceException {

		T pago = this.get(oid);

		if( !pago.getEstadoPago().podesAnularte() )
			throw new ServiceException("pago.anular.estado.invalido");
		
		pago.anulate();

		updateCuentaCorrienteOnAnular( pago );
		
		try {

			getDAO().update(pago);

		} catch (DAOException e) {

			throw new ServiceException(e);

		} catch (Exception e) {

			throw new ServiceException(e);

		}

		return pago;
	}

	protected abstract void updateCuentaCorrienteOnAnular(T pago) throws ServiceException;
}