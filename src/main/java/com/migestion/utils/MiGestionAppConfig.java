package com.migestion.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.PersistenceContextException;
import com.migestion.model.CategoriaProducto;
import com.migestion.model.Cliente;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.model.CondicionIVA;
import com.migestion.model.Sucursal;
import com.migestion.model.TipoCliente;
import com.migestion.model.TipoDocumento;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.model.Vendedor;
import com.migestion.services.ServiceFactory;

public class MiGestionAppConfig {

	@Before
	public void setUp() throws Exception {
		
		PersistenceContext.getInstance().beginTransaction();
	}

	@After
	public void setDown() throws Exception {
		
		PersistenceContext.getInstance().close();
	}
	
	@Test
	public void datosIniciales(){
		
		try {
		
			Cliente clienteMostrador = new Cliente();
			clienteMostrador.setNombre("CLIENTE MOSTRADOR");
			clienteMostrador.setTipoDocumento(TipoDocumento.DNI);
			clienteMostrador.setTipoCliente(TipoCliente.FRECUENTE);
			clienteMostrador.setCondicionIVA(CondicionIVA.CONSUMIDOR_FINAL);
			ServiceFactory.getClienteService().add(clienteMostrador);
			
			Vendedor vendedorTitular = new Vendedor();
			vendedorTitular.setNombre("TITULAR COMERCIO");
			ServiceFactory.getVendedorService().add(vendedorTitular);
			
			Sucursal sucursal = new Sucursal();
			sucursal.setNombre("CASA MATRIZ");
			sucursal.setNumero("1");
			ServiceFactory.getSucursalService().add(sucursal);
			
			CategoriaProducto cp = new CategoriaProducto();
			cp.setNombre("RUBRO GENERAL");
			ServiceFactory.getCategoriaProductoService().add(cp);

			ConceptoMovimiento concepto = new ConceptoMovimiento();
			concepto.setNombre("Venta");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Saldo Inicial");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Anulación gasto");
			ServiceFactory.getConceptoMovimientoService().add(concepto);
			
			concepto = new ConceptoMovimiento();
			concepto.setNombre("Pago venta");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Orden compra");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Pago orden compra");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Anulación orden compra");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			concepto = new ConceptoMovimiento();
			concepto.setNombre("Anulación venta");
			ServiceFactory.getConceptoMovimientoService().add(concepto);

			PersistenceContext.getInstance().commit();
		
		} catch (Exception e) {
			
			try {
				PersistenceContext.getInstance().rollback();
			} catch (PersistenceContextException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		}
		
		
	}
}
