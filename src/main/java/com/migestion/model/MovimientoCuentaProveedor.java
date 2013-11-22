package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Movimiento de cuenta corriente de proveedor
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
@Entity
@Table(name="movimiento_cuenta_proveedor")
public class MovimientoCuentaProveedor extends MovimientoCuenta{

	
	/**
	 * proveedor.
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="proveedor_oid")
	@NotFound(action=NotFoundAction.IGNORE)
	private Proveedor proveedor;
	
	
	public MovimientoCuentaProveedor(){
		
		super();
	}


	@Override
	public void setSaldoCuenta(Float monto) {
		
		getProveedor().setSaldo(monto);
	}



	@Override
	public Float getSaldoCuenta() {
		return getProveedor().getSaldo();
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}