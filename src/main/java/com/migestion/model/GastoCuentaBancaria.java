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
 * Detalle de un gasto con cuenta bancaria.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/12/2013
 *
 */

@Entity
@Table(name="gasto_cuenta_bancaria")
public class GastoCuentaBancaria extends Gasto{

	/**
	 * cuenta bancaria
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cuenta_bancaria_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{gasto.cuentaBancaria.required}")
	private CuentaBancaria cuentaBancaria;
	
	
	public GastoCuentaBancaria(){
		
	}


	@Override
	public FormaPago getFormaPago() {
		return FormaPago.CUENTA_BANCARIA;
	}



	@Override
	public MovimientoCuenta createMovimiento() {
		MovimientoCuentaBancaria movimiento = new MovimientoCuentaBancaria();
		movimiento.setCuentaBancaria(getCuentaBancaria());
		return movimiento;
	}
	
	/**
	 * @return the destino
	 */
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}


	/**
	 * @param destino the destino to set
	 */
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
		
		((MovimientoCuentaBancaria)getMovimiento()).setCuentaBancaria(cuentaBancaria);
        
	}


}