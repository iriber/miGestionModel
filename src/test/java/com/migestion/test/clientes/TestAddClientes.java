
package com.migestion.test.clientes;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.Cliente;
import com.migestion.model.CondicionIVA;
import com.migestion.model.EstadoProducto;
import com.migestion.model.TipoCliente;
import com.migestion.services.IClienteService;
import com.migestion.services.ServiceFactory;
import com.migestion.test.TestUtils;

/**
 * Test para agregar varios clientes 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class TestAddClientes{

	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	@Test
	public void test() {
		
		try {
	
			IClienteService service = ServiceFactory.getClienteService();
			for (int i = 0; i < 20; i++) {
				
				Cliente c = generarCliente();
				
				service.add( c );
				
				System.out.println(c);
			}
			
			PersistenceContext.getInstance().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}


	private Cliente generarCliente() {
		Cliente c = new Cliente();
		c.setNombre(  TestUtils.getRandomApellido() + " " +  TestUtils.getRandomNombre());
		c.setCondicionIVA( (CondicionIVA)TestUtils.getRandom( CondicionIVA.values() )  );
		c.setCuit(TestUtils.getRandomInt(20, 30)+ "-" + TestUtils.getTextoNumero(8) + "-" + TestUtils.getRandomInt(1, 9));
		c.setTipoCliente( (TipoCliente)TestUtils.getRandom( TipoCliente.values() )  );
		c.setObservaciones( TestUtils.getTexto( TestUtils.getRandomInt(50, 250) ) );
		return c;
	}

}
