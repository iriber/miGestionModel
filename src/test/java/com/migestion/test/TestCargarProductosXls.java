package com.migestion.test;


import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.model.CategoriaProducto;
import com.migestion.model.Producto;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.IProductoService;
import com.migestion.services.ServiceFactory;
import com.migestion.utils.XlsProductoReader;
import com.migestion.utils.XlsReader;

/**
 * Test para readxls
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class TestCargarProductosXls{


	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		PersistenceContext.getInstance().beginTransaction();
	}
	
	
	@Test
	public void test() {
		
		try {
	
			CategoriaProducto categoria = ServiceFactory.getCategoriaProductoService().get(ValoresPredefinidos.CATEGORIA_PRODUCTO_GENERAL);
			
			XlsReader reader = new XlsProductoReader();
			
			Collection<Object> productos = reader.readXls( "/META-INF/inventario.xls" );
			
			IProductoService service = ServiceFactory.getProductoService();
			for (Object object : productos) {
				
				Producto p = (Producto)object;
				p.setCategoria(categoria);
				service.add( p );
				
			}
			
			PersistenceContext.getInstance().commit();
			

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}

}
