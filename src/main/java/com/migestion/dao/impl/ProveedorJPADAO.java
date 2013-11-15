package com.migestion.dao.impl;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.ProveedorQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Proveedor;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.ProveedorCriteria;

/**
 * Implementación del dao jpa para proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public class ProveedorJPADAO extends GenericJPADAO<Proveedor, ProveedorCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public Proveedor get(Long oid) throws DAOException {

		return getEntityManager().find( Proveedor.class, oid );
	}


	@Override
	protected QueryBuilder<Proveedor> getQueryBuilder(Criteria criteria) {
		return new ProveedorQueryBuilder(criteria);
	}


	public Boolean hasDependencies(Proveedor entity) throws DAOException {

		Boolean ok = false;
		
		if( !ok ){
			//buscamos si tiene órdenes de compra.
//			VentaCriteria ventaCriteria = new VentaCriteria();
//			ventaCriteria.setProveedor(entity);
//			ok = DAOFactory.getVentaDAO().getListSize(ventaCriteria) > 0;
		}
		
		//pagos?
		
		//notas de crédito?
		
		return ok;
	}
}
