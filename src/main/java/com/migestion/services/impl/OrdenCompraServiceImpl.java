package com.migestion.services.impl;

import java.util.Date;
import java.util.List;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IOperacionDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.DetalleOperacion;
import com.migestion.model.DetalleOrdenCompra;
import com.migestion.model.EstadisticaOperacion;
import com.migestion.model.EstadoOrdenCompra;
import com.migestion.model.EstadoProducto;
import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.model.OrdenCompra;
import com.migestion.services.IOrdenCompraService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.OperacionCriteria;
import com.migestion.services.criteria.OrdenCompraCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Implementación del servicio para órdenes de compra.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 */

public class OrdenCompraServiceImpl extends GenericService<OrdenCompra, OrdenCompraCriteria>
		implements IOrdenCompraService {

	/**
	 * dao para maejar la persistencia de las órdenes de compra.
	 */
	private IGenericDAO<OrdenCompra, OrdenCompraCriteria> ordenCompraDAO;

	/**
	 * instancia para singleton.
	 */
	private static OrdenCompraServiceImpl instance;

	public static OrdenCompraServiceImpl getInstance() {

		if (instance == null)
			instance = new OrdenCompraServiceImpl();

		return instance;
	}

	/**
	 * @param ordenCompraDAO
	 *            the ordenCompraDAO to set
	 */
	private OrdenCompraServiceImpl() {

		ordenCompraDAO = DAOFactory.getOrdenCompraDAO();

	}

	@Override
	protected IGenericDAO<OrdenCompra, OrdenCompraCriteria> getDAO() {
		return ordenCompraDAO;
	}

	@Override
	protected void validateOnAdd(OrdenCompra entity) throws ServiceException {
		
		//requeridos: fecha, cliente, vendedor, sucursal, productos 
		if( entity.getFecha() == null ){
			throw new ServiceException( Messages.ORDEN_COMPRA_FECHA_REQUERIDA );
		}
		
		if( entity.getProveedor() == null ){
			throw new ServiceException( Messages.ORDEN_COMPRA_PROVEEDOR_REQUERIDO );
		}
		
		if( entity.getVendedor() == null ){
			throw new ServiceException( Messages.ORDEN_COMPRA_VENDEDOR_REQUERIDO );
		}
		
		if( entity.getSucursal() == null ){
			throw new ServiceException( Messages.ORDEN_COMPRA_SUCURSAL_REQUERIDA );
		}
		
		if( entity.getDetalles().size() == 0 ){
			throw new ServiceException( Messages.ORDEN_COMPRA_PRODUCTOS_REQUERIDOS );
		}
		
		
		//validar los detalles.
		
		//que no haya productos inactivos?
		
		//TODO que la suma de los detalles sea igual al monto de la ordenCompra.
		Float totalDetalles = 0F;
		for (DetalleOperacion detalle : entity.getDetalles()) {
			
			totalDetalles += detalle.getSubtotal();
			
			if( detalle.getProducto().getEstadoProducto().equals( EstadoProducto.INACTIVO ))
				throw new ServiceException( Messages.ORDEN_COMPRA_PRODUCTOS_INACTIVOS );	
			
		}
		
//		if( entity.getMonto().toString() != totalDetalles.toString() ){
//			throw new ServiceException( Messages.ORDEN_COMPRA_MONTO_INCORRECTO + "  " + entity.getMonto() + " " + totalDetalles);
//		}
		
		
	}

	@Override
	protected void validateOnUpdate(OrdenCompra entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateOnDelete(OrdenCompra entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void add(OrdenCompra entity) throws ServiceException {

		// la seteamos como impaga.
		entity.setEstadoOrdenCompra(EstadoOrdenCompra.IMPAGA);

		// pongo monto debe como el total de la ordenCompra y el pagado en 0
		entity.setMontoPagado(0F);
		entity.setMontoDebe(entity.getMonto());

		// agregamos stock a los productos (dada la cantidad entregada de cada uno).
		this.updatStock( entity.getDetalles() , 1);
		
		// agregamos la ordenCompra.
		super.add(entity);
		

		if( entity.getProveedor().getTieneCtaCte() ){

			//haber sobre la cuenta corriente del proveedor.
			MovimientoCuentaProveedor movimiento = new MovimientoCuentaProveedor();
			movimiento.setProveedor( entity.getProveedor() );
			movimiento.setHaber( entity.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoOrdenCompra() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("OrdenCompra # " + entity.getOid() );
			ServiceFactory.getMovimientoCuentaProveedorService().add(movimiento);
			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.
	 * GenericEntity)
	 */
	public void update(OrdenCompra entity) throws ServiceException {

		//TODO
		//super.add(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.migestion.services.IOrdenCompraService#getEstadisticaOrdenCompra(com.migestion
	 * .services.criteria.OrdenCompraCriteria)
	 */
	public EstadisticaOperacion getEstadistica(OperacionCriteria criteria)
			throws ServiceException {

		//excluimos las ordenCompras anuladas
		((OrdenCompraCriteria)criteria).addEstadoExcluir( EstadoOrdenCompra.ANULADA );
		
		return ((IOperacionDAO) getDAO()).getEstadisticaOperacion(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.migestion.services.IOrdenCompraService#anularOrdenCompra(java.lang.Long)
	 */
	public void anularOrdenCompra(Long oid) throws ServiceException {

		OrdenCompra ordenCompra = this.get(oid);

		if( !ordenCompra.getEstadoOrdenCompra().podesAnularte() )
			throw new ServiceException("ordenCompra.anular.estado.invalido");
		
		//restablecemos el stock de los productos dada la cantidad entregada de los mismos.
		this.updatStock( ordenCompra.getDetalles() , -1);

		//no anulo los pagos. genero una nota de crédito porque el dinero no se devuelve.
		//en caso de devolver el dinero podemos hacer que se cobre la nota de crédito.
		

		//FIXME actualizamos el saldo del proveedor ??? revisar si debemos actualizarlo o no
		if( ordenCompra.getProveedor().getTieneCtaCte() ){

			//debe sobre la cuenta corriente del proveedor.
			MovimientoCuentaProveedor movimiento = new MovimientoCuentaProveedor();
			movimiento.setProveedor( ordenCompra.getProveedor() );
			movimiento.setDebe( ordenCompra.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoAnulacionOrdenCompra() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("OrdenCompra # " + ordenCompra.getOid() );
			ServiceFactory.getMovimientoCuentaProveedorService().add(movimiento);
			
		}

		ordenCompra.setEstadoOrdenCompra(EstadoOrdenCompra.ANULADA);

		try {

			getDAO().update(ordenCompra);

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

		OrdenCompra ordenCompra = this.get(oid);

		//reestablecemos el stock de los productos.
		this.updatStock( ordenCompra.getDetalles() , 1);

		//actualizamos el saldo del proveedor
		if( ordenCompra.getProveedor().getTieneCtaCte() ){

			//TODO debe sobre la cuenta corriente del proveedor.
//			MovimientoCuentaProveedor movimiento = new MovimientoCuentaProveedor();
//			movimiento.setProveedor( ordenCompra.getProveedor() );
//			movimiento.setDebe( ordenCompra.getMonto() );
//			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoAnulacionOrdenCompra() );
//			movimiento.setFechaHora( new Date() );
//			movimiento.setDescripcion("OrdenCompra # " + ordenCompra.getOid() );
//			ServiceFactory.getMovimientoCuentaProveedorService().add(movimiento);
			
		}
		
		super.delete(oid);

	}

	/**
	 * actualizamos el stock de los productos de los detalles de ordenCompra
	 * @param detalles
	 * @param factor 1 | -1, para indicar si se suman o se restan
	 * @throws ServiceException
	 */
	private void updatStock(List<DetalleOperacion> detalles, Integer factor) throws ServiceException {

		for (DetalleOperacion detalle : detalles ) {
			Integer entregada = ((DetalleOrdenCompra)detalle).getCantidadEntregada();
			if(entregada!=null && entregada>0)
			ServiceFactory.getProductoService().addStock(detalle.getProducto(),
					(((DetalleOrdenCompra)detalle).getCantidadEntregada() * factor));

		}
	}
}