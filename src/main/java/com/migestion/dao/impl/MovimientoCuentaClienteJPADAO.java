package com.migestion.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.migestion.dao.IMovimientoCuentaDAO;
import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.MovimientoCuentaClienteQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.Balance;
import com.migestion.model.MovimientoCuentaCliente;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.MovimientoCuentaClienteCriteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;

/**
 * Implementación del dao jpa para movimiento de cuentas
 * corrientes de clientes
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
public class MovimientoCuentaClienteJPADAO extends GenericJPADAO<MovimientoCuentaCliente, MovimientoCuentaClienteCriteria> implements IMovimientoCuentaDAO{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public MovimientoCuentaCliente get(Long oid) throws DAOException {

		return getEntityManager().find( MovimientoCuentaCliente.class, oid );
	}


	@Override
	protected QueryBuilder<MovimientoCuentaCliente> getQueryBuilder(Criteria criteria) {
		return new MovimientoCuentaClienteQueryBuilder(criteria);
	}


	public Balance getBalance(MovimientoCuentaCriteria criteria) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery( Object[].class );
		Root<MovimientoCuentaCliente> cuentaClienteRoot = criteriaQuery.from( MovimientoCuentaCliente.class );
		
		criteriaQuery.select(   builder.array( 
									builder.count( cuentaClienteRoot.<Long>get( "oid" ) ),  
									builder.sum( cuentaClienteRoot.<Double>get( "debe" ) ), 
									builder.sum( cuentaClienteRoot.<Double>get( "haber" ) ) 
								) 
							);
		 
		MovimientoCuentaClienteQueryBuilder queryBuilder = new MovimientoCuentaClienteQueryBuilder(criteria);
		criteriaQuery.where( queryBuilder.getPredicates(cuentaClienteRoot, builder, criteria));

		Object[] object = (Object[])getEntityManager().createQuery(criteriaQuery).getSingleResult();;

		Balance balance = new Balance();		
		balance.setCantidadMovimientos( ((Long)object[0]).intValue() );
		
		if( balance.getCantidadMovimientos() > 0 ){
			balance.setDebe( ((Double)object[1]).floatValue() );
			balance.setHaber( ((Double)object[2]).floatValue() );
		}else{
			balance.setDebe( 0F );
			balance.setHaber( 0f );
		}
		
		return balance;
	}


	public Boolean hasDependencies(MovimientoCuentaCliente entity)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
