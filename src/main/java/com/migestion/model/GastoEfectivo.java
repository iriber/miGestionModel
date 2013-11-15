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
 * Detalle de un gasto en efectivo.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */

@Entity
@Table(name="gasto_efectivo")
public class GastoEfectivo extends Gasto{

	/**
	 * caja desde donde se retira el efectivo
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="caja_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{gasto.caja.required}")
	private Caja caja;
	
	
	public GastoEfectivo(){
		super();
	}

	@Override
	public MovimientoCuenta createMovimiento() {
		MovimientoCaja movimiento = new MovimientoCaja();
		movimiento.setCaja( getCaja() );
		return movimiento;
	}
	
	
	@Override
	public FormaPago getFormaPago() {
		return FormaPago.EFECTIVO;
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
		
		((MovimientoCaja)getMovimiento()).setCaja(caja);
		
	}
	
}