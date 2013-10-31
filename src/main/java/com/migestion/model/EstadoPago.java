package com.migestion.model;




/**
 * Estado de un pago
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public enum EstadoPago{

	REALIZADO("estado.pago.realizado"),
	ANULADO("estado.pago.anulado");
	
	
	//nombre.
	private String nombre;

	
	private EstadoPago(String nombre){
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

	public boolean podesAnularte() {
		
		return !this.equals( ANULADO );
		
	}

	public boolean podesEliminarte() {
		
		return !this.equals( ANULADO );
	}
	
}