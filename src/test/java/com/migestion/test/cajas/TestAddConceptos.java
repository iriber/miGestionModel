
package com.migestion.test.cajas;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.Cliente;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.model.CondicionIVA;
import com.migestion.model.TipoCliente;
import com.migestion.services.IConceptoMovimientoService;
import com.migestion.services.ServiceFactory;
import com.migestion.test.TestUtils;

/**
 * Test para agregar varios conceptos 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
public class TestAddConceptos{

	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	@Test
	public void test() {
		
		try {
	
			IConceptoMovimientoService service = ServiceFactory.getConceptoMovimientoService();
				
			service.add( generarConceptoMovimiento("Luz") );
			service.add( generarConceptoMovimiento("Agua") );
			service.add( generarConceptoMovimiento("ABL") );
			service.add( generarConceptoMovimiento("Internet") );
			service.add( generarConceptoMovimiento("Teléfono") );
			service.add( generarConceptoMovimiento("Varios") );
			service.add( generarConceptoMovimiento("Insumos") );
			service.add( generarConceptoMovimiento("Supermercado") );
			service.add( generarConceptoMovimiento("Pago a proveedores") );
			service.add( generarConceptoMovimiento("Gas") );
			PersistenceContext.getInstance().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}


	private ConceptoMovimiento generarConceptoMovimiento(String nombre) {
		ConceptoMovimiento c = new ConceptoMovimiento();
		c.setNombre( nombre );
		return c;
	}

}
