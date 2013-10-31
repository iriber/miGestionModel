package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Movimiento de caja
 * 
 * Representa un movimiento de caja
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
@Entity
@Table(name="movimiento_caja")
public class MovimientoCaja extends MovimientoCuenta{

	
	/**
	 * caja
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="caja_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Caja caja;
	
	public MovimientoCaja(){
		
		super();
	}
	
	/**
	 * @return the caja
	 */
	public Caja getCaja() {
		return caja;
	}


	/**
	 * @param caja the caja to set
	 */
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void setSaldoCuenta(Float monto) {
		getCaja().setSaldo(monto);
	}

	@Override
	public Float getSaldoCuenta() {
		return getCaja().getSaldo();
	}


	
}