package com.migestion.dao.impl;

import com.migestion.dao.DAOFactory;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CategoriaProductoQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.CategoriaProducto;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.CategoriaProductoCriteria;
import com.migestion.services.criteria.ProductoCriteria;

/**
 * Implementación del dao jpa para categorías de producto
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class CategoriaProductoJPADAO extends GenericJPADAO<CategoriaProducto, CategoriaProductoCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public CategoriaProducto get(Long oid) throws DAOException {

		return getEntityManager().find( CategoriaProducto.class, oid );
	}


	@Override
	protected QueryBuilder<CategoriaProducto> getQueryBuilder(Criteria criteria) {
		return new CategoriaProductoQueryBuilder(criteria);
	}


	public Boolean hasDependencies(CategoriaProducto entity)
			throws DAOException {
		
		Boolean ok = false;

		
		//buscamos si tiene categorías hijas.
		CategoriaProductoCriteria categoriaProductoCriteria = new CategoriaProductoCriteria();
		categoriaProductoCriteria.setPadre(entity);
		ok = getListSize(categoriaProductoCriteria) > 0;
		
		if( !ok ){
			//buscamos si tiene productos asociados.
			ProductoCriteria productoCriteria = new ProductoCriteria();
			productoCriteria.setCategoriaProducto(entity);
			ok = DAOFactory.getProductoDAO().getListSize(productoCriteria) > 0;
		}
		
		return ok;
	}
}
