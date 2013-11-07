package com.migestion.test.ventas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.PersistenceContextException;
import com.migestion.model.DetalleVenta;
import com.migestion.model.Venta;
import com.migestion.services.IGenericService;
import com.migestion.services.IVentaService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.VentaCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar ventas
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class TestAnularVenta{

	private IVentaService service;
	
	@Before
	public void setUp() throws Exception {
		
		service = getService();
		
		PersistenceContext.getInstance().beginTransaction();

	}

	@Test
	public void test() {
		
		try {
		
			//agregamos la entity
			//service.anularVenta( 32768L );
			
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
	

	protected IVentaService getService() {
		return ServiceFactory.getVentaService();
	}

}
