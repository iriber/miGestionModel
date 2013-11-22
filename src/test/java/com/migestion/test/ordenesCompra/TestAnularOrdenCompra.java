package com.migestion.test.ordenesCompra;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.PersistenceContextException;
import com.migestion.services.IOrdenCompraService;
import com.migestion.services.ServiceFactory;

/**
 * Test para anular órdenes de compra
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class TestAnularOrdenCompra{

	private IOrdenCompraService service;
	
	@Before
	public void setUp() throws Exception {
		
		service = getService();
		
		PersistenceContext.getInstance().beginTransaction();

	}

	@Test
	public void test() {
		
		try {
		
			//agregamos la entity
			service.anularOrdenCompra( 12L );
			
			//TODO chequear
			
			PersistenceContext.getInstance().commit();
			
			
		} catch (Exception e) {
			
			try {
				PersistenceContext.getInstance().rollback();
			} catch (PersistenceContextException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			fail( e.getMessage() );
			
		}
	}
	

	protected IOrdenCompraService getService() {
		return ServiceFactory.getOrdenCompraService();
	}

}
