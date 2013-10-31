package com.migestion.model;

import java.io.Serializable;


/**
 * Estadística de pagos
 * 
 * @author Bernardo Iribarne
 * @since 28/10/2013
 */
public class EstadisticaPago implements Serializable{

	private Float importeTotal;
	
	private Integer cantidad;
	
	public Float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Float getImportePromedio() {
		Float promedio = 0F;
		if( getCantidad() != null && getCantidad() > 0)			
			promedio = getImporteTotal() / getCantidad();
		return promedio;
		
	}
	

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
}