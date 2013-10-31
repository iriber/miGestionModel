package com.migestion.services;

import com.migestion.model.Producto;
import com.migestion.services.criteria.ProductoCriteria;
import com.migestion.services.exception.ServiceException;

/**
 * Interface para servicio de productos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public interface IProductoService extends IGenericService<Producto, ProductoCriteria>{
 
	/**
	 * se desactiva un producto.
	 * @param producto
	 * @throws ServiceException
	 */
	public void desactivar(Producto producto)  throws ServiceException ;
	
	/**
	 * se activa un producto.
	 * @param producto
	 * @throws ServiceException
	 */
	public void activar(Producto producto)  throws ServiceException ;

	/**
	 * se agrega stock al producto
	 * @param producto
	 * @param cantidad
	 * @throws ServiceException
	 */
	public void addStock(Producto producto, Integer cantidad )  throws ServiceException ;
}
