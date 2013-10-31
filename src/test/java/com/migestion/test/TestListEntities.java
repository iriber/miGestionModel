package com.migestion.test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.migestion.model.GenericEntity;
import com.migestion.services.exception.ServiceException;

/**
 * Test para listar entities.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public abstract class TestListEntities<T extends GenericEntity> {


	protected abstract List<T> list() throws ServiceException;
	
	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		
	}
	
	
	@Test
	public void test() {
		
		try {
	
			//buscamos las personas.			
			List<T> entities = list();
			
			//imprimimos los resultados
			for (T entity: entities) {
				
				System.out.println( "Entity: " + entity.toString() );
				
			}

			//chequeamos que no sea null
			assertNotNull( entities );
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}

}
