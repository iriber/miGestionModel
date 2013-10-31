package com.migestion.model;


/**
 * Estado de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public enum EstadoCaja{

	ABIERTA("estado.caja.abierta"),
	CERRADA("estado.caja.cerrada");
	
	
	//nombre.
	private String nombre;

	
	private EstadoCaja(String nombre){
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

	public boolean podesCerrarte() {
		
		return !this.equals( CERRADA );
		
	}

}