package com.migestion.utils;

import com.migestion.dao.PersistenceContext;
import com.migestion.dao.exception.PersistenceContextException;
import com.migestion.model.Cliente;
import com.migestion.model.ValoresPredefinidos;
import com.migestion.services.ServiceFactory;

public class MiGestionAppConfig {

	public static void main(String[] args) {
		
		MiGestionAppConfig config = new MiGestionAppConfig();
		config.datosIniciales();
	}
	
	public void datosIniciales(){
		
		try {
		
			PersistenceContext.getInstance().beginTransaction();
		
			Cliente clienteMostrador = new Cliente();
			clienteMostrador.setOid( 100L);// ValoresPredefinidos.CLIENTE_MOSTRADOR );
			clienteMostrador.setNombre("CLIENTE MOSTRADORES");
			
			ServiceFactory.getClienteService().add(clienteMostrador);
			
			
		
			PersistenceContext.getInstance().commit();
		
		} catch (Exception e) {
			
			try {
				PersistenceContext.getInstance().rollback();
			} catch (PersistenceContextException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		}finally{
			
			try {
				PersistenceContext.getInstance().close();
				
			} catch (PersistenceContextException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
