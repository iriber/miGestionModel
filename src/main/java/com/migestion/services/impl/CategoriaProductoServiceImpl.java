package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.CategoriaProducto;
import com.migestion.services.ICategoriaProductoService;
import com.migestion.services.criteria.CategoriaProductoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para categorías de producto.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 */

public class CategoriaProductoServiceImpl extends GenericService<CategoriaProducto, CategoriaProductoCriteria> implements ICategoriaProductoService{

	/**
	 * dao para maejar la persistencia de los productos.
	 */
	private IGenericDAO<CategoriaProducto, CategoriaProductoCriteria> categoriaDAO;

	/**
	 * instancia para singleton.
	 */
	private static CategoriaProductoServiceImpl instance;
	
	
	public static CategoriaProductoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new CategoriaProductoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param categoriaDAO the productoDAO to set
	 */
	private CategoriaProductoServiceImpl() {
		
		categoriaDAO = DAOFactory.getCategoriaProductoDAO();
		
	}


	@Override
	protected IGenericDAO<CategoriaProducto, CategoriaProductoCriteria> getDAO() {
		return categoriaDAO;
	}


	@Override
	protected void validateOnAdd(CategoriaProducto entity) throws ServiceException {

		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CATEGORIA_PRODUCTO_NOMBRE_REQUERIDO );
		}
		
		//nombre único
		CategoriaProductoCriteria criteria = new CategoriaProductoCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		if( getListSize(criteria) > 0 ){
			throw new ServiceException( Messages.CATEGORIA_PRODUCTO_NOMBRE_REPETIDO );
		}
	}

	@Override
	protected void validateOnUpdate(CategoriaProducto entity) throws ServiceException {

		//requeridos: nombre
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.CATEGORIA_PRODUCTO_NOMBRE_REQUERIDO );
		}

		//nombre único
		CategoriaProductoCriteria criteria = new CategoriaProductoCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		if( getListSize(criteria) > 0 ){
			throw new ServiceException( Messages.CATEGORIA_PRODUCTO_NOMBRE_REPETIDO );
		}
		
	}

	@Override
	protected void validateOnDelete(CategoriaProducto entity) throws ServiceException {
		
		//que no tenga dependencias
		
		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.CATEGORIA_PRODUCTO_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
		
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	

}
