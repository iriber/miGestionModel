package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
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
	
		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CLIENTE_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		ClienteCriteria criteria = new ClienteCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro cliente con el mismo nombre, exigimos que ingrese
			//el nro de documento
			if( entity.getNroDocumento()==null ){
		
				throw new ServiceException( Messages.CLIENTE_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el nroDocumento
				criteria.setNroDocumento( entity.getNroDocumento() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.CLIENTE_DUPLICADO );
			}	
			
		}
	
		
	}

	@Override
	protected void validateOnUpdate(Cliente entity) throws ServiceException {
		
		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CLIENTE_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		ClienteCriteria criteria = new ClienteCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro cliente con el mismo nombre, exigimos que ingrese
			//el nro de documento
			if( entity.getNroDocumento()==null ){
		
				throw new ServiceException( Messages.CLIENTE_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el nroDocumento
				criteria.setNroDocumento( entity.getNroDocumento() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.CLIENTE_DUPLICADO );
			}	
			
		}
	}

	@Override
	protected void validateOnDelete(Cliente entity) throws ServiceException {
		
		//que no esté afectado en ninguna operación.
		
		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.CLIENTE_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void add(Cliente entity) throws ServiceException {

		entity.setSaldo(0F);
		
		super.add(entity);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void update(Cliente entity) throws ServiceException {

		
		super.update(entity);
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