package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

	/**
	 * cheque
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="cheque_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Cheque cheque;
	
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


	public Cheque getCheque() {
		return cheque;
	}


	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

}