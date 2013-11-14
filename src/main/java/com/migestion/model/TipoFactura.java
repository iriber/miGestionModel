package com.migestion.model;

/**
 * Tipo de factura
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
public enum TipoFactura{

	A("tipo.factura.a"),
	B("tipo.factura.b"),
	C("tipo.factura.c"),
	E("tipo.factura.e");
	
	
	//nombre.
	private String nombre;

	
	private TipoFactura(String nombre){
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
