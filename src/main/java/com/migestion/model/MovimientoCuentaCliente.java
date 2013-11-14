package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Movimiento de cuenta corriente de cliente
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 14/11/2013
 *
 */
@Entity
@Table(name="movimiento_cuenta_cliente")
public class MovimientoCuentaCliente extends MovimientoCuenta{

	
	/**
	 * cliente.
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="cliente_oid")
	@NotFound(action=NotFoundAction.IGNORE)
	private Cliente cliente;
	
	
	public MovimientoCuentaCliente(){
		
		super();
	}


	@Override
	public void setSaldoCuenta(Float monto) {
		
		getCliente().setSaldo(monto);
	}



	@Override
	public Float getSaldoCuenta() {
		return getCliente().getSaldo();
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}