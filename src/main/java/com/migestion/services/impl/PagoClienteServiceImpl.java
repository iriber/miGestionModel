package com.migestion.services.impl;


import java.util.Calendar;
import java.util.Date;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.i18n.Messages;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.model.NotaCredito;
import com.migestion.model.PagoCliente;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.PagoClienteCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para pagos de clientes.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 */

public class PagoClienteServiceImpl extends PagoServiceImpl<PagoCliente, PagoClienteCriteria>{

	/**
	 * instancia para singleton.
	 */
	private static PagoClienteServiceImpl instance;
	
	
	public static PagoClienteServiceImpl getInstance(){
		
		if( instance == null )
			instance = new PagoClienteServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param productoDAO the productoDAO to set
	 */
	private PagoClienteServiceImpl() {
		
		super();
		
	}



	@Override
	protected void validateOnAdd(PagoCliente entity) throws ServiceException {
		
		
		if( entity.getCliente() == null ){
			throw new ServiceException( Messages.PAGO_CLIENTE_REQUERIDO );
		}
		
		super.validateOnAdd(entity);
	}

	
	protected void updateCuentaCorrienteOnAdd(PagoCliente pago) throws ServiceException{
		
		if( pago.getCliente().getTieneCtaCte() ){

			//haber sobre la cuenta corriente del cliente.
			MovimientoCuentaCliente movimiento = new MovimientoCuentaCliente();
			movimiento.setCliente( pago.getCliente() );
			movimiento.setHaber( pago.getMonto() );
			movimiento.setConcepto( ServiceFactory.getConceptoMovimientoService().getConceptoPagoVenta() );
			movimiento.setFechaHora( new Date() );
			movimiento.setDescripcion("Pago # " + pago.getOid() );
			ServiceFactory.getMovimientoCuentaClienteService().add(movimiento);
			
		}
	}

	protected void updateCuentaCorrienteOnAnular(PagoCliente pago) throws ServiceException{
		
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

	}

	@Override
	protected IGenericDAO<PagoCliente, PagoClienteCriteria> buildDAO() {
		return DAOFactory.getPagoClienteDAO();
	}
}