package com.migestion.model;

import java.awt.Color;



/**
 * Estado de una venta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 23/10/2013
 *
 */
public enum EstadoVenta{

	IMPAGA("estado.venta.impaga", new Color(0xFA5858), new Color(0xFFFFFF)),
	PAGADA("estado.venta.pagada", new Color(0xD0F5A9), new Color(0x000000)),
	PAGADA_PARCIALMENTE("estado.venta.pagada_parcialmente", new Color(0xF7FE2E), new Color(0x000000)),
	ANULADA("estado.venta.anulada", new Color(0xFF0000), new Color(0xFFFFFF));
	
	
	//nombre.
	private String nombre;
	private Color bg;
	private Color fg;

	
	private EstadoVenta(String nombre, Color bg, Color fg){
		this.nombre = nombre;
		this.bg = bg;
		this.fg = fg;
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

	/**
	 * @return the bg
	 */
	public Color getBg() {
		return bg;
	}

	/**
	 * @return the fg
	 */
	public Color getFg() {
		return fg;
	}

	public boolean podesAnularte() {
		
		return !this.equals( ANULADA );
		
	}

	public boolean podesEliminarte() {
		
		return this.equals( IMPAGA );
	}
	
}