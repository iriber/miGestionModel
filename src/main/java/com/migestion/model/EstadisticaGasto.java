package com.migestion.model;

import java.io.Serializable;


/**
 * Estadística de gastos
 * 
 * @author Bernardo Iribarne
 * @since 12/11/2013
 */
public class EstadisticaGasto implements Serializable{

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