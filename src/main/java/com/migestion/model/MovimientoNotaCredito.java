package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Movimiento para nota de crédito
 * 
 * Representa un movimiento de valor a debitar ( nota de crédito )
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */
@Entity
@Table(name="movimiento_notaCredito")
public class MovimientoNotaCredito extends MovimientoCuenta{

	
	public MovimientoNotaCredito(){
		
		super();
	}
	

	@Override
	public void setSaldoCuenta(Float monto) {

		//TODO
	}

	@Override
	public Float getSaldoCuenta() {
		//TODO
		return 0F;
	}

}