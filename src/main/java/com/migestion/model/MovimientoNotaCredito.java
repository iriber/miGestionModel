package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

	/**
	 * nota de crédito
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="nota_credito_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private NotaCredito notaCredito;
	
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


	public NotaCredito getNotaCredito() {
		return notaCredito;
	}


	public void setNotaCredito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
	}

}