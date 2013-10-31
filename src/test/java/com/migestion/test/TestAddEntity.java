package com.migestion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.GenericEntity;
import com.migestion.services.IGenericService;
import com.migestion.services.criteria.Criteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.services.impl.GenericService;


/**
 * Test para agregar entities.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public abstract class TestAddEntity<T extends GenericEntity, TCriteria extends Criteria> {

	private Long cantidad;
	private IGenericService<T, TCriteria> service;
	private TCriteria criteria;
	private T entity;
	
	protected abstract IGenericService<T, TCriteria> getService();
	
	protected abstract T getEntity() throws ServiceException;
	
	protected abstract TCriteria getCriteria();
	
	@Before
	public void setUp() throws Exception {
		
		service = getService();
		
		entity = getEntity();
		
		criteria = getCriteria();
		
		//cantidad actual de entities.
		cantidad = service.getListSize(criteria);
		
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	
	@Test
	public void test() {
		
		try {
		
			//agregamos la entity
			service.add( entity );
			
			//chequeamos que la cantidad de entities se haya incrementado en 1.
			Long cantidadActual = service.getListSize(criteria);
			assertEquals( cantidad.longValue()+1, cantidadActual.longValue()  );
			
			PersistenceContext.getInstance().commit();
			
			
		} catch (Exception e) {
			PersistenceContext.getInstance().rollback();
			
			e.printStackTrace();
			fail( e.getMessage() );
			
		}
	}
	
}