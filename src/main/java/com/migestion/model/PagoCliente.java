package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Pago de cliente
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
@Entity
@Table(name="pago_cliente")
public class PagoCliente extends Pago{

	/**
	 * cliente
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cliente_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{operacion.cliente.required}")
	private Cliente cliente;
	

	public PagoCliente(){
		super();
	}
	
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}