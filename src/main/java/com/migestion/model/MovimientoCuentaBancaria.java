package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Movimiento de cuenta bancaria
 * 
 * Representa un movimiento de cuenta bancaria
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
@Entity
@Table(name="movimiento_cuenta_bancaria")
public class MovimientoCuentaBancaria extends MovimientoCuenta{

	
	/**
	 * cuenta bancaria.
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="cuenta_bancaria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private CuentaBancaria cuentaBancaria;
	
	
	public MovimientoCuentaBancaria(){
		
		super();
	}

	/**
	 * @return the cuentaBancaria
	 */
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}


	/**
	 * @param cuentaBancaria the cuentaBancaria to set
	 */
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}



	@Override
	public void setSaldoCuenta(Float monto) {
		
		getCuentaBancaria().setSaldo(monto);
	}



	@Override
	public Float getSaldoCuenta() {
		return getCuentaBancaria().getSaldo();
	}

}