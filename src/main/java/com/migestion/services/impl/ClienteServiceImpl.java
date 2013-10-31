package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.model.Cliente;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IClienteService;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para clientes.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 */

public class ClienteServiceImpl extends GenericService<Cliente, ClienteCriteria> implements IClienteService{

	/**
	 * dao para maejar la persistencia de los clientes.
	 */
	private IGenericDAO<Cliente, ClienteCriteria> clienteDAO;

	/**
	 * instancia para singleton.
	 */
	private static ClienteServiceImpl instance;
	
	
	public static ClienteServiceImpl getInstance(){
		
		if( instance == null )
			instance = new ClienteServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param clienteDAO the clienteDAO to set
	 */
	private ClienteServiceImpl() {
		
		clienteDAO = DAOFactory.getClienteDAO();
		
	}


	@Override
	protected IGenericDAO<Cliente, ClienteCriteria> getDAO() {
		return clienteDAO;
	}


	@Override
	protected void validateOnAdd(Cliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void validateOnUpdate(Cliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(Cliente entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void add(Cliente entity) throws ServiceException {

		//TODO ver si agregamos la cuenta corriente.
		
		super.add(entity);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void update(Cliente entity) throws ServiceException {

		
		super.add(entity);
	}

	public Cliente getClienteMostrador() throws ServiceException {
		
		return this.get(ValoresPredefinidos.CLIENTE_MOSTRADOR);
	}


	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 *
	public void delete(Long oid) throws ServiceException {

		Cliente p = this.get(oid);
		
		p.setEstadoCliente(EstadoCliente.ELIMINADO);
		
		this.update(p);
		
	}*/
}