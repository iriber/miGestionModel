package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
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
		
		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.VENDEDOR_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		VendedorCriteria criteria = new VendedorCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro vendedor con el mismo nombre, exigimos que ingrese
			//el nro de documento
			if( entity.getNroDocumento()==null ){
		
				throw new ServiceException( Messages.VENDEDOR_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el nroDocumento
				criteria.setNroDocumento( entity.getNroDocumento() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.VENDEDOR_DUPLICADO );
			}	
			
		}
	}

	@Override
	protected void validateOnUpdate(Vendedor entity) throws ServiceException {
		

		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.VENDEDOR_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		VendedorCriteria criteria = new VendedorCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro cliente con el mismo nombre, exigimos que ingrese
			//el nro de documento
			if( entity.getNroDocumento()==null ){
		
				throw new ServiceException( Messages.VENDEDOR_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el nroDocumento
				criteria.setNroDocumento( entity.getNroDocumento() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.VENDEDOR_DUPLICADO );
			}	
			
			//throw new ServiceException( Messages.CLIENTE_NOMBRE_REPETIDO );
			
		}
	}

	@Override
	protected void validateOnDelete(Vendedor entity) throws ServiceException {
		
		//que no esté afectado en ninguna operación.
		
		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.VENDEDOR_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
		
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

		
		super.update(entity);
	}

	public Vendedor getTitularComercio() throws ServiceException {
		return get(ValoresPredefinidos.VENDEDOR_TITULAR_COMERCIO);
	}


}