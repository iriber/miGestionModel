package com.migestion.test.balances;


import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.migestion.model.Balance;
import com.migestion.services.ServiceFactory;

/**
 * Test para balance de cajas
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
public class TestBalanceCajas{


	
	@Before
	public void setUp() throws Exception {
		
		//inicializar el contexto
		
	}
	
	
	@Test
	public void test() {
		
		try {
	
			Balance balance = ServiceFactory.getBalanceService().getBalanceCajas( new Date() );
			
			System.out.println( "debe: " + balance.getDebe() );
			System.out.println( "debe: " + balance.getHaber() );
			System.out.println( "saldo: " + balance.getSaldo() );
			System.out.println( "#movimientos: " + balance.getCantidadMovimientos() );
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}

}
