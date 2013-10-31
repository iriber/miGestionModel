package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Movimiento para cheque
 * 
 * Representa un movimiento de valor a depositar (cheque, transferencia? )
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
@Entity
@Table(name="movimiento_cheque")
public class MovimientoCheque extends MovimientoCuenta{

	
	public MovimientoCheque(){
		
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