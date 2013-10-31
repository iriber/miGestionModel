package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.IPagoDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.model.DetalleFormaPago;
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
		
		//TODO chequear los montos totals: monto pago == monto pagado en ventas == monto de los detalles de forma de pago
		
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

		// TODO chequear contraoperación dada la forma de pago.
		
		// TODO generar una nota de crédito al cliente.
		
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