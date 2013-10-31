package com.migestion.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.migestion.test.cajas.TestAddCaja;
import com.migestion.test.cajas.TestAddConceptoMovimiento;
import com.migestion.test.clientes.TestAddCliente;
import com.migestion.test.cuentasBancarias.TestAddCuentaBancaria;
import com.migestion.test.productos.TestAddCategoriaProducto;
import com.migestion.test.productos.TestAddProducto;
import com.migestion.test.sucursales.TestAddSucursal;
import com.migestion.test.vendedores.TestAddVendedor;


/**
 * Test para agregar entities.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestAddCategoriaProducto.class
				, TestAddProducto.class
				, TestAddCliente.class
				, TestAddVendedor.class
				, TestAddSucursal.class
				, TestAddCuentaBancaria.class
				, TestAddConceptoMovimiento.class
				, TestAddCaja.class})
public class TestSuiteInicializar{

	
	
	@Test
	public void test() {
		
	}	
}