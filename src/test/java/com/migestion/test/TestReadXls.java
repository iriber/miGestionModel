package com.migestion.test;


import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.migestion.utils.XlsProductoReader;
import com.migestion.utils.XlsReader;

/**
 * Test para readxls
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class TestReadXls{


	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		
	}
	
	
	@Test
	public void test() {
		
		try {
	
			XlsReader reader = new XlsProductoReader();
			
			Collection<Object> productos = reader.readXls( "/META-INF/inventario.xls" );
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}

}
