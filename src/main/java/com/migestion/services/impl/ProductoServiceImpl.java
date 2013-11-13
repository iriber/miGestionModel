package com.migestion.services.impl;


import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.IGenericDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.i18n.Messages;
import com.migestion.model.EstadoProducto;
import com.migestion.model.Producto;
import com.migestion.services.IProductoService;
import com.migestion.services.criteria.ProductoCriteria;
import com.migestion.services.exception.ServiceException;


/**
 * Implementación del servicio para productos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 */

public class ProductoServiceImpl extends GenericService<Producto, ProductoCriteria> implements IProductoService{

	/**
	 * dao para maejar la persistencia de los productos.
	 */
	private IGenericDAO<Producto, ProductoCriteria> productoDAO;

	/**
	 * instancia para singleton.
	 */
	private static ProductoServiceImpl instance;
	
	
	public static ProductoServiceImpl getInstance(){
		
		if( instance == null )
			instance = new ProductoServiceImpl();
		
		return instance;
	}
	
	/**
	 * @param productoDAO the productoDAO to set
	 */
	private ProductoServiceImpl() {
		
		productoDAO = DAOFactory.getProductoDAO();
		
	}


	@Override
	protected IGenericDAO<Producto, ProductoCriteria> getDAO() {
		return productoDAO;
	}


	@Override
	protected void validateOnAdd(Producto entity) throws ServiceException {
	
		//requeridos: nombre, precio, iva
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.PRODUCTO_NOMBRE_REQUERIDO );
		}
		
		if( entity.getPrecio() == null ){
			throw new ServiceException( Messages.PRODUCTO_PRECIO_REQUERIDO );
		}

		if( entity.getIva() == null ){
			throw new ServiceException( Messages.PRODUCTO_IVA_REQUERIDO );
		}
		
		if( entity.getCategoria() == null ){
			throw new ServiceException( Messages.PRODUCTO_CATEGORIA_REQUERIDA );
		}
		
		//nombre único
		ProductoCriteria criteria = new ProductoCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		if( getListSize(criteria) > 0 ){
			throw new ServiceException( Messages.PRODUCTO_NOMBRE_REPETIDO );
		}
		
	}

	@Override
	protected void validateOnUpdate(Producto entity) throws ServiceException {
		
		//requeridos: nombre, precio, iva
		if( StringUtils.isEmpty(entity.getNombre()) ){
			throw new ServiceException( Messages.PRODUCTO_NOMBRE_REQUERIDO );
		}
		
		if( entity.getPrecio() == null ){
			throw new ServiceException( Messages.PRODUCTO_PRECIO_REQUERIDO );
		}

		if( entity.getIva() == null ){
			throw new ServiceException( Messages.PRODUCTO_IVA_REQUERIDO );
		}
		
		if( entity.getCategoria() == null ){
			throw new ServiceException( Messages.PRODUCTO_CATEGORIA_REQUERIDA );
		}
		
		//nombre único
		ProductoCriteria criteria = new ProductoCriteria();
		criteria.setNombreEqual( entity.getNombre() );
		criteria.setOidNotEqual( entity.getOid() );
		if( getListSize(criteria) > 0 ){
			throw new ServiceException( Messages.PRODUCTO_NOMBRE_REPETIDO );
		}
	}

	@Override
	protected void validateOnDelete(Producto entity) throws ServiceException {

		//que no esté afectado en ninguna operación.
		
		try {
			if( getDAO().hasDependencies(entity) ){
				throw new ServiceException( Messages.PRODUCTO_TIENE_DEPENDENCIAS );
			}
		} catch (DAOException e) {
			throw new ServiceException( e );
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void add(Producto entity) throws ServiceException {

		//si viene con stock 0 le seteo estado AGOTADO
		if( entity.getStockActual()!=null && entity.getStockActual() == 0)
			entity.setEstadoProducto(EstadoProducto.AGOTADO);
		else
			entity.setEstadoProducto(EstadoProducto.ACTIVO);
		
		super.add(entity);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#add(com.migestion.model.GenericEntity)
	 */
	public void update(Producto entity) throws ServiceException {

		//si viene con stock 0 y estaba activo le seteo estado AGOTADO
		if( entity.getStockActual()!=null && entity.getStockActual() == 0 && entity.getEstadoProducto().equals(EstadoProducto.ACTIVO))
			
			entity.setEstadoProducto(EstadoProducto.AGOTADO);

		
		//si viene con stock  y estaba agotado le seteo estado activo
		if( entity.getStockActual()!=null && entity.getStockActual() > 0 && entity.getEstadoProducto().equals(EstadoProducto.AGOTADO))
		
			entity.setEstadoProducto(EstadoProducto.ACTIVO);

		//si viene con stock  nulo y estaba agotado le seteo estado activo
		if( entity.getStockActual()==null  && entity.getEstadoProducto().equals(EstadoProducto.AGOTADO))
		
			entity.setEstadoProducto(EstadoProducto.ACTIVO);
		
		super.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IProductoService#inactivar(com.migestion.model.Producto)
	 */
	public void desactivar(Producto producto) throws ServiceException{
		
		producto.setEstadoProducto(EstadoProducto.INACTIVO);
		this.update(producto);
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IProductoService#activar(com.migestion.model.Producto)
	 */
	public void activar(Producto producto) throws ServiceException{
		
		producto.setEstadoProducto(EstadoProducto.ACTIVO);
		this.update(producto);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.IProductoService#addStock(com.migestion.model.Producto, java.lang.Integer)
	 */
	public void addStock(Producto producto, Integer cantidad)
			throws ServiceException {
		
		
		//esto lo hacemos si el producto tiene seteado el stock actual
		//sino significado que no se lleva control de stock
		if( producto.getStockActual() != null ){
			
			producto.setStockActual( producto.getStockActual() + cantidad );
			this.update(producto);
		}
		
	}	

	/*
	 * (non-Javadoc)
	 * @see com.migestion.services.impl.GenericService#delete(java.lang.Long)
	 *
	public void delete(Long oid) throws ServiceException {

		Producto p = this.get(oid);
		
		p.setEstadoProducto(EstadoProducto.ELIMINADO);
		
		this.update(p);
		
	}*/
}