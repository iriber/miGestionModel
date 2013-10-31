package com.migestion.model;



/**
 * Condiciones de iva
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public enum CondicionIVA{

	CONSUMIDOR_FINAL("condicion.iva.consumidor.final"),
	RESPONSABLE_INSCRIPTO("condicion.iva.responsable.inscripto"),
	RESPONSABLE_NO_INSCRIPTO("condicion.iva.responsable.no.inscripto"),
	RESPONSABLE_MONOTRIBUTO("condicion.iva.responsable.monotributo"),
	EXENTO("condicion.iva.exento");
	
	
	//nombre.
	private String nombre;

	
	private CondicionIVA(String nombre){
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return  nombre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

}
