package com.migestion.services.impl;


import java.util.Calendar;
import java.util.Date;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.DetalleFormaPago;
import com.migestion.model.DetalleOperacion;
import com.migestion.model.DetallePago;
import com.migestion.model.EstadisticaPago;
import com.migestion.model.EstadoPago;
import com.migestion.model.EstadoProducto;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.model.NotaCredito;
import com.migestion.model.Pago;
import com.migestion.services.IPagoService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para pagos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 */

public class PagoServiceImpl extends GenericService<Pago, PagoCriteria> implements IPagoService{

	/**
	 * dao para maejar la persistencia de los pagos.
	 */
	private IGenericDAO<Pago, PagoCriteria> pagoDAO;

	/**
	 * instancia para singleton.
	 */
	private static PagoServiceImpl instance;
	
	
	public static PagoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new PagoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param pagoDAO the pagoDAO to set
	 */
	private PagoServiceImpl() {
		
		pagoDAO = DAOFactory.getPagoDAO();
		
	}


	@Override
	protected IGenericDAO<Pago, PagoCriteria> getDAO() {
		return pagoDAO;
	}


	@Override
	protected void validateOnAdd(Pago entity) throws ServiceException {
		
		//requeridos: fecha, cliente, sucursal, ventas a pagar 
		if( entity.getFecha() == null ){
			throw new ServiceException( Messages.PAGO_FECHA_REQUERIDA );
		}
		
		if( entity.getCliente() == null ){
			throw new ServiceException( Messages.PAGO_CLIENTE_REQUERIDO );
		}
		
		if( entity.getDetallesPago().size() == 0 ){
			throw new ServiceException( Messages.PAGO_VENTAS_REQUERIDAS );
		}

		if( entity.getDetallesFormaPago().size() == 0 ){
			throw new ServiceException( Messages.PAGO_FORMAS_PAGO_REQUERIDAS );
		}

		//validar los detalles.
		
		//que no haya ventas anuladas ni pagadas
		
		//TODO que la suma de los detalles sea igual al monto de la venta.
		Float totalDetalles = 0F;
		for (DetallePago detalle : entity.getDetallesPago()) {
			
			totalDetalles += detalle.getMonto();
			
//			if( !detalle.getOperacion().podesPagarte() )
//				throw new ServiceException( Messages.PAGO_VENTAS_INCORRECTAS );	
			
		}

	}

	@Override
	protected void validateOnUpdate(Pago entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Pago entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Pago entity) throws ServiceException {

		// lo seteamos como realizado.
		entity.setEstadoPago(EstadoPago.REALIZADO);

		//hay que actualizar los saldos de las cuentas afectadas (detalles forma pago).
		for (DetalleFormaPago detalle : entity.getDetallesFormaPago()) {
			detalle.getMovimiento().calcularSaldos();
		}
		
		
		if( entity.getCliente().getTieneCtaCte() ){

			//haber sobre la cuenta corriente del cliente.
			MovimientoCuentaCliente movimiento = new MovimientoCuentaCliente();
			movimiento.setCliente( entity.getCliente() );
			movimiento.setHaber( entity.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoPagoVenta() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("Pago # " + entity.getOid() );
			ServiceFactory.getMovimientoCuentaClienteService().add(movimiento);
			
		}
		// agregamos el pago.
		super.add(entity);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IPagoService#getEstadisticaPago(com.migestion.services.criteria.PagoCriteria)
	 */
	public EstadisticaPago getEstadisticaPago(PagoCriteria criteria)
			throws ServiceException {

		//excluimos las ventas anuladas
		criteria.addEstadoExcluir( EstadoPago.ANULADO );
		
		return ((IPagoDAO) getDAO()).getEstadisticaPago(criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IPagoService#anularPago(java.lang.Long)
	 */
	public Pago anularPago(Long oid) throws ServiceException {

		Pago pago = this.get(oid);

		if( !pago.getEstadoPago().podesAnularte() )
			throw new ServiceException("pago.anular.estado.invalido");
		
		pago.anulate();

		//TODO deberíamos recibir por parámetro para que se pueda editar el 
		//número, sucursal, vendedor y fecha de vencimiento.
		NotaCredito notaCredito = new NotaCredito();
		//c.setNumero("021545454");
		notaCredito.setCliente( pago.getCliente() );
		//notaCredito.setVendedor( pago.getVendedor() );
		notaCredito.setSucursal( pago.getSucursal() );
		notaCredito.setFecha(new Date());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( notaCredito.getFecha() );
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		Date fechaVenc = calendar.getTime();
		notaCredito.setFechaVencimiento(fechaVenc);
		notaCredito.setMonto( pago.getMonto() );
		ServiceFactory.getNotaCreditoService().add(notaCredito);

		try {

			getDAO().update(pago);

		} catch (DAOException e) {

			throw new ServiceException(e);

		} catch (Exception e) {

			throw new ServiceException(e);

		}

		return pago;
	}

}