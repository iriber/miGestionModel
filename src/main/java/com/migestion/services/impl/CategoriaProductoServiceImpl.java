package com.migestion.services.impl;


import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
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
		// TODO Auto-generated method stub
		System.out.println(entity.getNombre());
	}

	@Override
	protected void validateOnUpdate(CategoriaProducto entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateOnDelete(CategoriaProducto entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')" )
	

}
