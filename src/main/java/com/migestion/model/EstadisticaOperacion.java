package com.migestion.model;

import java.io.Serializable;


/**
 * Estadística
 * 
 * @author Bernardo Iribarne
 * @since 23/10/2013
 */
public class EstadisticaOperacion implements Serializable{

	private Float importeTotal;
	
	private Float importeTotalPagado;
	
	private Float importeTotalDebe;
	
	private Integer cantidad;
	
	public Float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Float getImporteTotalDebe() {
		return this.importeTotalDebe;
	}

	public Float getImportePromedio() {
		Float promedio = 0F;
		if( getCantidad() != null && getCantidad() > 0)			
			promedio = getImporteTotal() / getCantidad();
		return promedio;
		
	}
	
	public Float getImporteTotalPagado() {
		return importeTotalPagado;
	}

	public void setImporteTotalPagado(Float importeTotalPagado) {
		this.importeTotalPagado = importeTotalPagado;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @param importeTotalDebe the importeTotalDebe to set
	 */
	public void setImporteTotalDebe(Float importeTotalDebe) {
		this.importeTotalDebe = importeTotalDebe;
	}

	
	
}
