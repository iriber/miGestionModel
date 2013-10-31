package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.Vendedor;
import com.migestion.services.IVendedorService;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para vendedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 */

public class VendedorServiceImpl extends GenericService<Vendedor, VendedorCriteria> implements IVendedorService{

	/**
	 * dao para maejar la persistencia de los vendedores.
	 */
	private IGenericDAO<Vendedor, VendedorCriteria> vendedorDAO;

	/**
	 * instancia para singleton.
	 */
	private static VendedorServiceImpl instance;
	
	
	public static VendedorServiceImpl getInstance(){
		
		if( instance == null )
			instance = new VendedorServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param vendedorDAO the vendedorDAO to set
	 */
	private VendedorServiceImpl() {
		
		vendedorDAO = DAOFactory.getVendedorDAO();
		
	}


	@Override
	protected IGenericDAO<Vendedor, VendedorCriteria> getDAO() {
		return vendedorDAO;
	}


	@Override
	protected void validateOnAdd(Vendedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(Vendedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Vendedor entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void add(Vendedor entity) throws ServiceException {

		//TODO ver si agregamos la cuenta corriente.
		
		super.add(entity);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void update(Vendedor entity) throws ServiceException {

		
		super.add(entity);
	}

	public Vendedor getTitularComercio() throws ServiceException {
		return get(ValoresPredefinidos.VENDEDOR_TITULAR_COMERCIO);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 *
	public void delete(Long oid) throws ServiceException {

		Vendedor p = this.get(oid);
		
		p.setEstadoVendedor(EstadoVendedor.ELIMINADO);
		
		this.update(p);
		
	}*/
}