package com.migestion.test.productos;


import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.Balance;
import com.migestion.model.CategoriaProducto;
import com.migestion.model.EstadoProducto;
import com.migestion.model.Producto;
import com.migestion.services.IProductoService;
import com.migestion.services.ServiceFactory;
import com.migestion.test.TestUtils;

/**
 * Test para agregar varios productos 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 08/11/2013
 *
 */
public class TestAddProductos{

	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	@Test
	public void test() {
		
		try {
	
			CategoriaProducto categoria = ServiceFactory.getCategoriaProductoService().get(1L);
			
			IProductoService service = ServiceFactory.getProductoService();
			for (int i = 0; i < 5000; i++) {
				
				Producto p = generarProducto(categoria);
				
				service.add( p );
				
				System.out.println(p);
			}
			
			PersistenceContext.getInstance().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}


	private Producto generarProducto(CategoriaProducto categoria) {
		Producto p = new Producto();
		p.setNombre( TestUtils.getTexto(TestUtils.getRandomInt(10, 20)));
		p.setDescripcion(TestUtils.getTexto(TestUtils.getRandomInt(30, 100)));
		p.setPrecio( TestUtils.getRandomFLoat(20, 400));
		p.setIva(21F);
		p.setCodigoBarras( TestUtils.getTextoNumero(20) );
		p.setEstadoProducto(EstadoProducto.ACTIVO);
		p.setStockActual( TestUtils.getRandomInt(0, 150));
		p.setStockMinimo(TestUtils.getRandomInt(5, 10));
		p.setStockMaximo(TestUtils.getRandomInt(50, 150));
		p.setObservaciones( TestUtils.getTexto( TestUtils.getRandomInt(50, 250) ) );
		
		
		p.setCategoria(categoria);
		return p;
	}

}
