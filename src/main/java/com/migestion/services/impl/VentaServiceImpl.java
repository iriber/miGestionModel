package com.migestion.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IVentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.model.DetalleOperacion;
import com.migestion.model.EstadisticaVenta;
import com.migestion.model.EstadoPago;
import com.migestion.model.EstadoVenta;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.model.NotaCredito;
import com.migestion.model.Operacion;
import com.migestion.model.Pago;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.Venta;
import com.migestion.services.IVentaService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Implementación del servicio para ventas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 */

public class VentaServiceImpl extends GenericService<Venta, VentaCriteria>
		implements IVentaService {

	/**
	 * dao para maejar la persistencia de los ventas.
	 */
	private IGenericDAO<Venta, VentaCriteria> ventaDAO;

	/**
	 * instancia para singleton.
	 */
	private static VentaServiceImpl instance;

	public static VentaServiceImpl getInstance() {

		if (instance == null)
			instance = new VentaServiceImpl();

		return instance;
	}

	/**
	 * @param ventaDAO
	 *            the ventaDAO to set
	 */
	private VentaServiceImpl() {

		ventaDAO = DAOFactory.getVentaDAO();

	}

	@Override
	protected IGenericDAO<Venta, VentaCriteria> getDAO() {
		return ventaDAO;
	}

	@Override
	protected void validateOnAdd(Venta entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateOnUpdate(Venta entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateOnDelete(Venta entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(Venta entity) throws ServiceException {

		// la seteamos como impaga.
		entity.setEstadoVenta(EstadoVenta.IMPAGA);

		// pongo monto debe como el total de la venta y el pagado en 0
		entity.setMontoPagado(0F);
		entity.setMontoDebe(entity.getMonto());

		// descontamos el stock de los productos.
		this.updatStock( entity.getDetalles() , -1);
		
		// agregamos la venta.
		super.add(entity);
		

		if( entity.getCliente().getTieneCtaCte() ){

			//debe sobre la cuenta corriente del cliente.
			MovimientoCuentaCliente movimiento = new MovimientoCuentaCliente();
			movimiento.setCliente( entity.getCliente() );
			movimiento.setDebe( entity.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoVentas() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("Venta # " + entity.getOid() );
			ServiceFactory.getMovimientoCuentaClienteService().add(movimiento);
			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void update(Venta entity) throws ServiceException {

		//TODO
		//super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.migestion.services.IVentaService#getEstadisticaVenta(com.migestion
	 * .services.criteria.VentaCriteria)
	 */
	public EstadisticaVenta getEstadisticaVenta(VentaCriteria criteria)
			throws ServiceException {

		//excluimos las ventas anuladas
		criteria.addEstadoExcluir( EstadoVenta.ANULADA );
		
		return ((IVentaDAO) getDAO()).getEstadisticaVenta(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.IVentaService#anularVenta(java.lang.Long)
	 */
	public void anularVenta(Long oid) throws ServiceException {

		Venta venta = this.get(oid);

		if( !venta.getEstadoVenta().podesAnularte() )
			throw new ServiceException("venta.anular.estado.invalido");
		
		// restablecemos el stock de los productos.
		this.updatStock( venta.getDetalles() , 1);

//		//tengo que salir a buscar los pagos que afectan a la venta
//		//si el pago afecta a otras ventas hay que reconstruirlo
//		//quitando el detalle de pago sobre esta venta
//		//y generar una nota de crédito sobre el monto de esta venta
//		
//		//buscamos todos los pagos vigentes sobre la operacion
//		PagoCriteria criteria = new PagoCriteria();
//		criteria.setOperacion( venta );
//		criteria.addEstadoExcluir(EstadoPago.ANULADO);
//		List<Pago> pagos = ServiceFactory.getPagoService().list(criteria);
//		
//		//anulamos estos pagos
//		for (Pago pago : pagos) {
//			
//			ServiceFactory.getPagoService().anularPago( pago.getOid() );
//			
//		}
//		
//		//reconstruimos los pagos pero sin la venta a anular.
//		for (Pago pago : pagos) {
//			
//			//pagamos las operaciones quitando las venta anulada.
//			List<Operacion> operaciones = pago.getOperaciones();
//			operaciones.remove( venta );
//			
//			if( !operaciones.isEmpty() ){
//				Pago pagoReconstruido = new Pago();
//				pagoReconstruido.setCliente( pago.getCliente() );
//				pagoReconstruido.setSucursal( pago.getSucursal() );
//				pagoReconstruido.setObservaciones( pago.getObservaciones() );
//				pagoReconstruido.setFecha( pago.getFecha() );
//				
//				//el monto total del pago será lo del pago anterior menos el de esta venta anulada.
//				Float montoPago = pago.getMonto() - pago.getMontoPagado( venta );
//				
//				pagoReconstruido.pagar(montoPago, operaciones );
//				
//				//agregamos el pago.
//				ServiceFactory.getPagoService().add( pagoReconstruido );
//			}
//		}

		
		//no anulo los pagos. genero una nota de crédito porque el dinero no se devuelve.
		//en caso de devolver el dinero podemos hacer que se cobre la nota de crédito.
		
		//TODO deberíamos recibir por parámetro para que se pueda editar el 
		//número, sucursal, vendedor y fecha de vencimiento.
		NotaCredito notaCredito = new NotaCredito();
		//c.setNumero("021545454");
		notaCredito.setCliente( venta.getCliente() );
		notaCredito.setVendedor( venta.getVendedor() );
		notaCredito.setSucursal( venta.getSucursal() );
		notaCredito.setFecha(new Date());
		notaCredito.setObservaciones("Anulación de venta #" + venta.getOid());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( notaCredito.getFecha() );
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		Date fechaVenc = calendar.getTime();
		notaCredito.setFechaVencimiento(fechaVenc);
		notaCredito.setMonto( venta.getMontoPagado() );
		ServiceFactory.getNotaCreditoService().add(notaCredito);
		
		venta.setEstadoVenta(EstadoVenta.ANULADA);

		try {

			getDAO().update(venta);

		} catch (DAOException e) {

			throw new ServiceException(e);

		} catch (Exception e) {

			throw new ServiceException(e);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 */
	public void delete(Long oid) throws ServiceException {

		Venta venta = this.get(oid);

		// restablecemos el stock de los productos.
		this.updatStock( venta.getDetalles() , 1);

		super.delete(oid);

	}

	/**
	 * actualizamos el stock de los productos de los detalles de venta
	 * @param detalles
	 * @param factor 1 | -1, para indicar si se suman o se restan
	 * @throws ServiceException
	 */
	private void updatStock(List<DetalleOperacion> detalles, Integer factor) throws ServiceException {

		for (DetalleOperacion detalle : detalles ) {

			ServiceFactory.getProductoService().addStock(detalle.getProducto(),
					(detalle.getCantidad() * factor));

		}
	}
}