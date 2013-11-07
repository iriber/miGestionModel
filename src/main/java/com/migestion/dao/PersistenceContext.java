package com.migestion.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;

import com.migestion.dao.exception.PersistenceContextException;

public class PersistenceContext {

	private static String defaultUnit = "migestion";
	
	private static PersistenceContext instance;
	
	/**
	 * map con los entity managers disponibles.
	 * @var Map
	 */
	private HashMap<String, EntityManager> emMap;
	
	
	private EntityManagerFactory emf;
	private EntityManager em;
			
	private PersistenceContext(){
	
		//emf = Persistence.createEntityManagerFactory("migestion");
		//em = emf.createEntityManager();
		emMap = new HashMap<String, EntityManager>(); 
		
	}
	
	public static PersistenceContext getInstance(){
		
		if( instance == null)
			instance = new PersistenceContext();
		
		return instance;
	}
	
	/**
	 * 
	 * se obtiene el entity manager asociado a la unidad de 
	 * persistencia indicada. 
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.

	 */
	public EntityManager getEntityManager(String unitName){
		

		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
		
		
		if ( emMap.get(unitName) == null ){
			emf = Persistence.createEntityManagerFactory(unitName);
			em = emf.createEntityManager();
			emMap.put(unitName, em); 
		}else{
//				if(emMap.get(unitName)->getConnection()->isConnected()){
//					$this->em->get($unitName)->getConnection()->connect();
//				}
			em = emMap.get(unitName);
			if(! em.isOpen() ){
				emf = Persistence.createEntityManagerFactory(unitName);
				em = emf.createEntityManager();
				emMap.put(unitName, em);
			}
		}
		
		
		return emMap.get(unitName);	

		
	}

	/**
	 * se inicia una transaccion en la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 * @throws PersistenceContextException 
	 */
	public void beginTransaction(String unitName) throws PersistenceContextException{

		try {
			
			if(StringUtils.isEmpty(unitName))
				unitName = defaultUnit;
				
			EntityManager entityManager = getEntityManager( unitName );
			
			entityManager.getTransaction().begin();
			
		} catch (RuntimeException e) {
			
			throw new PersistenceContextException(e);
		}
		
	}
	
	/**
	 * se realiza el commit sobre  la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 */
	public void commit(String unitName) throws PersistenceContextException{

		try {
			
			if(StringUtils.isEmpty(unitName))
				unitName = defaultUnit;
				
			EntityManager entityManager = getEntityManager( unitName );
			
			entityManager.getTransaction().commit();
			
		} catch (RuntimeException e) {
			
			throw new PersistenceContextException(e);
		}

	}

	/**
	 * se realiza el rolback sobre  la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string unitName nombre de la unidad de persistencia.
	 */
	public void rollback(String unitName) throws PersistenceContextException{

		try {
			
			if(StringUtils.isEmpty(unitName))
				unitName = defaultUnit;
				
			EntityManager entityManager = getEntityManager( unitName );
			
			if( entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			
			entityManager.clear();
		} catch (RuntimeException e) {
			
			throw new PersistenceContextException(e);
		}

	}	
	
	/**
	 * se cierra la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 */
	public void close(String unitName) throws PersistenceContextException{

		try {
			
			if(StringUtils.isEmpty(unitName))
				unitName = defaultUnit;
				
			EntityManager entityManager = getEntityManager( unitName );
			
			entityManager.close();
			
			emMap.remove(unitName);
			
		} catch (RuntimeException e) {
			
			throw new PersistenceContextException(e);
		}

	}
	
	public EntityManager getEntityManager(){
		return this.getEntityManager("");
	}
	
	public void beginTransaction() throws PersistenceContextException{
		this.beginTransaction("") ;
	}
	
	public void commit() throws PersistenceContextException{
		this.commit("");
	}
	
	public void rollback() throws PersistenceContextException{
		this.rollback("");
	}
	public void close() throws PersistenceContextException{
		this.close("");
	}
}