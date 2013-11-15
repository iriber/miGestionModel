package com.migestion.dao.impl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.migestion.dao.exception.DAOException;
import com.migestion.dao.helper.CuentaBancariaQueryBuilder;
import com.migestion.dao.helper.QueryBuilder;
import com.migestion.model.CuentaBancaria;
import com.migestion.model.DetalleFormaPagoDeposito;
import com.migestion.model.DetalleFormaPagoTarjeta;
import com.migestion.model.DetalleFormaPagoTransferencia;
import com.migestion.model.GastoCuentaBancaria;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.criteria.CuentaBancariaCriteria;

/**
 * Implementación del dao jpa para cuentasBancarias
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class CuentaBancariaJPADAO extends GenericJPADAO<CuentaBancaria, CuentaBancariaCriteria>{

	/*
	 * (non-Javadoc)
	 * @see com.migestion.dao.IGenericDAO#get(java.lang.Long)
	 */
	public CuentaBancaria get(Long oid) throws DAOException {

		return getEntityManager().find( CuentaBancaria.class, oid );
	}


	@Override
	protected QueryBuilder<CuentaBancaria> getQueryBuilder(Criteria criteria) {
		return new CuentaBancariaQueryBuilder(criteria);
	}


	public Boolean hasDependencies(CuentaBancaria entity) throws DAOException {
		
		Boolean ok = false;
		
		//buscamos si tiene movimientos? No, los movimientos deberían eliminarse
		
		//buscamos pagos con tarjeta sobre la cuenta bancaria
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<DetalleFormaPagoTarjeta> root = criteriaQuery.from(DetalleFormaPagoTarjeta.class);
		criteriaQuery.select( builder.count( root.<Long>get( "oid" ) ) );
		criteriaQuery.where( builder.equal( (root.<CuentaBancaria>get("destino")), entity ) );
		Query query = getEntityManager().createQuery(criteriaQuery);
		ok = (Long)query.getSingleResult() > 0;

		if(!ok){
			//buscamos pagos con transferencia sobre la cuenta bancaria
			builder = getEntityManager().getCriteriaBuilder();
			criteriaQuery = builder.createQuery(Long.class);
			Root<DetalleFormaPagoTransferencia> rootTransferencia = criteriaQuery.from(DetalleFormaPagoTransferencia.class);
			criteriaQuery.select( builder.count( rootTransferencia.<Long>get( "oid" ) ) );
			criteriaQuery.where( builder.equal( (rootTransferencia.<CuentaBancaria>get("destino")), entity ) );
			query = getEntityManager().createQuery(criteriaQuery);
			ok = (Long)query.getSingleResult() > 0;
		}
		
		if(!ok){
			//buscamos pagos con depósito sobre la cuenta bancaria
			builder = getEntityManager().getCriteriaBuilder();
			criteriaQuery = builder.createQuery(Long.class);
			Root<DetalleFormaPagoDeposito> rootDeposito = criteriaQuery.from(DetalleFormaPagoDeposito.class);
			criteriaQuery.select( builder.count( rootDeposito.<Long>get( "oid" ) ) );
			criteriaQuery.where( builder.equal( (rootDeposito.<CuentaBancaria>get("destino")), entity ) );
			query = getEntityManager().createQuery(criteriaQuery);
			ok = (Long)query.getSingleResult() > 0;
		}
		
		
		//buscamos gastos
		if(!ok){
			//buscamos gastos con la cuenta bancaria
			builder = getEntityManager().getCriteriaBuilder();
			criteriaQuery = builder.createQuery(Long.class);
			Root<GastoCuentaBancaria> rootGasto = criteriaQuery.from(GastoCuentaBancaria.class);
			criteriaQuery.select( builder.count( rootGasto.<Long>get( "oid" ) ) );
			criteriaQuery.where( builder.equal( (rootGasto.<CuentaBancaria>get("cuentaBancaria")), entity ) );
			query = getEntityManager().createQuery(criteriaQuery);
			ok = (Long)query.getSingleResult() > 0;
		}
		
		return ok;

	}
}