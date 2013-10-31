package com.migestion.model;

/**
 * tipos de documentos
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public enum TipoDocumento{

	DNI("tipo.documento.dni"),
	LE("tipo.documento.le"),
	LC("tipo.documento.lc"),
	PASAPORTE("tipo.documento.pasaporte"),
	CF("tipo.documento.cf");
	
	
	//nombre.
	private String nombre;

	
	private TipoDocumento(String nombre){
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
