package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.Proveedor;
import com.migestion.services.IProveedorService;
import com.migestion.services.criteria.ProveedorCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 */

public class ProveedorServiceImpl extends GenericService<Proveedor, ProveedorCriteria> implements IProveedorService{

	/**
	 * dao para maejar la persistencia de los proveedores.
	 */
	private IGenericDAO<Proveedor, ProveedorCriteria> proveedorDAO;

	/**
	 * instancia para singleton.
	 */
	private static ProveedorServiceImpl instance;
	
	
	public static ProveedorServiceImpl getInstance(){
		
		if( instance == null )
			instance = new ProveedorServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param proveedorDAO the proveedorDAO to set
	 */
	private ProveedorServiceImpl() {
		
		proveedorDAO = DAOFactory.getProveedorDAO();
		
	}


	@Override
	protected IGenericDAO<Proveedor, ProveedorCriteria> getDAO() {
		return proveedorDAO;
	}


	@Override
	protected void validateOnAdd(Proveedor entity) throws ServiceException {
	
		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.PROVEEDOR_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		ProveedorCriteria criteria = new ProveedorCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro proveedor con el mismo nombre, exigimos que ingrese
			//el cuit
			if( StringUtils.isEmpty(entity.getCuit()) ){
		
				throw new ServiceException( Messages.PROVEEDOR_NOMBRE_REPETIDO_CUIT_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el cuit
				criteria.setCuit( entity.getCuit() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.PROVEEDOR_DUPLICADO );
			}	
			
		}
	
		
	}

	@Override
	protected void validateOnUpdate(Proveedor entity) throws ServiceException {
		
		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.PROVEEDOR_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		ProveedorCriteria criteria = new ProveedorCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		
		if( getListSize(criteria) > 0 ){
		
			//si existe otro proveedor con el mismo nombre, exigimos que ingrese
			//el cuit
			if( StringUtils.isEmpty(entity.getCuit()) ){
		
				throw new ServiceException( Messages.PROVEEDOR_NOMBRE_REPETIDO_CUIT_REQUERIDO );
				
			}else{
				
				//vemos si además del nombre se repite el cuit
				criteria.setCuit( entity.getCuit() );		
				if( getListSize(criteria) > 0 )
					throw new ServiceException( Messages.PROVEEDOR_DUPLICADO );
			}	
			
		}
	}

	@Override
	protected void validateOnDelete(Proveedor entity) throws ServiceException {
		
		//que no esté afectado en ninguna operación.
		
		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.PROVEEDOR_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void add(Proveedor entity) throws ServiceException {

		entity.setSaldo(0F);
		
		super.add(entity);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void update(Proveedor entity) throws ServiceException {

		
		super.update(entity);
	}


}