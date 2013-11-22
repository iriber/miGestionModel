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
 * Pago de proveedor
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
@Entity
@Table(name="pago_proveedor")
public class PagoProveedor extends Pago{

	/**
	 * proveedor
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="proveedor_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{pago.proveedor.required}")
	private Proveedor proveedor;
	

	public PagoProveedor(){
		super();
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}