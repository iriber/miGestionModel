package com.migestion.services.impl;


import java.util.Date;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.i18n.Messages;
import com.migestion.model.MovimientoCuentaProveedor;
import com.migestion.model.PagoProveedor;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoProveedorCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para pagos de proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 */

public class PagoProveedorServiceImpl extends PagoServiceImpl<PagoProveedor, PagoProveedorCriteria>{

	/**
	 * instancia para singleton.
	 */
	private static PagoProveedorServiceImpl instance;
	
	
	public static PagoProveedorServiceImpl getInstance(){
		
		if( instance == null )
			instance = new PagoProveedorServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param productoDAO the productoDAO to set
	 */
	private PagoProveedorServiceImpl() {
		
		super();
		
	}



	@Override
	protected void validateOnAdd(PagoProveedor entity) throws ServiceException {
		
		
		if( entity.getProveedor() == null ){
			throw new ServiceException( Messages.PAGO_PROVEEDOR_REQUERIDO );
		}
		
		super.validateOnAdd(entity);
	}

	
	public void add(PagoProveedor entity) throws ServiceException {
		
		super.add(entity);
		
		if( entity.getProveedor().getTieneCtaCte() ){

			//debe sobre la cuenta corriente del cliente.
			MovimientoCuentaProveedor movimiento = new MovimientoCuentaProveedor();
			movimiento.setProveedor( entity.getProveedor() );
			movimiento.setDebe( entity.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoPagoOrdenCompra() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("Pago # " + entity.getOid() );
			ServiceFactory.getMovimientoCuentaProveedorService().add(movimiento);
			
		}
	}

	public PagoProveedor anularPago(Long oid) throws ServiceException {
		
//		//TODO deberíamos recibir por parámetro para que se pueda editar el 
//		//número, sucursal, vendedor y fecha de vencimiento.
//		NotaCredito notaCredito = new NotaCredito();
//		//c.setNumero("021545454");
//		notaCredito.setCliente( ((PagoCliente)pago).getCliente() );
//		//notaCredito.setVendedor( pago.getVendedor() );
//		notaCredito.setSucursal( pago.getSucursal() );
//		notaCredito.setFecha(new Date());
//		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime( notaCredito.getFecha() );
//		calendar.add(Calendar.DAY_OF_MONTH, 30);
//		Date fechaVenc = calendar.getTime();
//		notaCredito.setFechaVencimiento(fechaVenc);
//		notaCredito.setMonto( pago.getMonto() );
//		ServiceFactory.getNotaCreditoService().add(notaCredito);

		return super.anularPago(oid);
	}

	@Override
	protected IGenericDAO<PagoProveedor, PagoProveedorCriteria> buildDAO() {
		return DAOFactory.getPagoProveedorDAO();
	}
}