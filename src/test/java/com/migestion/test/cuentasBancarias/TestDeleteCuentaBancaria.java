
package com.migestion.test.cuentasBancarias;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.Cliente;
import com.migestion.model.CondicionIVA;
import com.migestion.model.EstadoProducto;
import com.migestion.model.TipoCliente;
import com.migestion.services.IClienteService;
import com.migestion.services.ICuentaBancariaService;
import com.migestion.services.ServiceFactory;
import com.migestion.test.TestUtils;

/**
 * Test para eliminar cuenta bancaria 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public class TestDeleteCuentaBancaria{

	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	@Test
	public void test() {
		
		try {
	
			ICuentaBancariaService service = ServiceFactory.getCuentaBancariaService();
			
			service.delete( 2L );	
			
			PersistenceContext.getInstance().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}



}
