package com.migestion.model;

/**
 * Tipo de cliente
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public enum TipoCliente{

	FRECUENTE("tipo.cliente.frecuente"),
	VIP("tipo.cliente.vip"),
	REVENDEDOR("tipo.cliente.revendedor"),
	DISTRIBUIDOR("tipo.cliente.distribuidor");
	
	
	//nombre.
	private String nombre;

	
	private TipoCliente(String nombre){
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
