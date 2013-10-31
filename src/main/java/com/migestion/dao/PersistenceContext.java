package com.migestion.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;

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
	public EntityManager getEntityManager(String unitName) {
		
		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
		
		
		if ( emMap.get(unitName) == null ){
			emf = Persistence.createEntityManagerFactory(unitName);
			em = emf.createEntityManager();
			emMap.put(unitName, em); 
		}else{
//			if(emMap.get(unitName)->getConnection()->isConnected()){
//				$this->em->get($unitName)->getConnection()->connect();
//			}
		}
		
		return emMap.get(unitName);	
	}

	/**
	 * se inicia una transaccion en la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 */
	public void beginTransaction(String unitName){

		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
			
		EntityManager entityManager = getEntityManager( unitName );
		
		entityManager.getTransaction().begin();
	}
	
	/**
	 * se realiza el commit sobre  la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 */
	public void commit(String unitName){

		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
			
		EntityManager entityManager = getEntityManager( unitName );
		
		entityManager.getTransaction().commit();
	}

	/**
	 * se realiza el rolback sobre  la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string unitName nombre de la unidad de persistencia.
	 */
	public void rollback(String unitName){

		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
			
		EntityManager entityManager = getEntityManager( unitName );
		
		entityManager.getTransaction().rollback();
	}	
	
	/**
	 * se cierra la unidad de persistencia indicada.
	 * si no se indica ninguna se toma la default.
	 * @param string $unitName nombre de la unidad de persistencia.
	 */
	public void close(String unitName){

		if(StringUtils.isEmpty(unitName))
			unitName = defaultUnit;
			
		EntityManager entityManager = getEntityManager( unitName );
		
		entityManager.close();
	}
	
	public EntityManager getEntityManager() {
		return this.getEntityManager("");
	}
	
	public void beginTransaction(){
		this.beginTransaction("");
	}
	
	public void commit(){
		this.commit("");
	}
	
	public void rollback(){
		this.rollback("");
	}
	public void close(){
		this.close("");
	}
}