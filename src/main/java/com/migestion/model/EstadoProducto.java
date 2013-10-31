package com.migestion.model;



/**
 * Estado de un producto
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public enum EstadoProducto{

	ACTIVO("activo"),
	INACTIVO("inactivo"),
	ELIMINADO("eliminado"),
	AGOTADO("agotado");
	
	
	//nombre.
	private String nombre;

	
	private EstadoProducto(String nombre){
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return  nombre;
	}

	public boolean podesActivarte() {
		
		return this.equals( INACTIVO ) || this.equals( ELIMINADO );
	}
	
	public boolean podesDesactivarte() {
		
		return this.equals( ACTIVO ) || this.equals( ELIMINADO ) || this.equals( AGOTADO );
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
}
