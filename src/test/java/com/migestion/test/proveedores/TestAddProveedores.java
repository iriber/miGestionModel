
package com.migestion.test.proveedores;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.CondicionIVA;
import com.migestion.model.Proveedor;
import com.migestion.services.IProveedorService;
import com.migestion.services.ServiceFactory;
import com.migestion.test.TestUtils;

/**
 * Test para agregar varios proveedores
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public class TestAddProveedores{

	private int cantidadCrear;
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
		
		this.cantidadCrear = 2;
		
	}
	
	
	@Test
	public void test() {
		
		try {
	
			IProveedorService service = ServiceFactory.getProveedorService();
			for (int i = 0; i < this.cantidadCrear; i++) {
				
				Proveedor c = generarProveedor();
				
				service.add( c );
				
				System.out.println(c);
			}
			
			PersistenceContext.getInstance().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}


	private Proveedor generarProveedor() {
		Proveedor c = new Proveedor();
		c.setNombre(  TestUtils.getRandomApellido() + " " +  TestUtils.getRandomNombre());
		c.setCondicionIVA( (CondicionIVA)TestUtils.getRandom( CondicionIVA.values() )  );
		c.setCuit(TestUtils.getRandomInt(20, 30)+ "-" + TestUtils.getTextoNumero(8) + "-" + TestUtils.getRandomInt(1, 9));
		c.setObservaciones( TestUtils.getTexto( TestUtils.getRandomInt(50, 250) ) );
		return c;
	}

}
