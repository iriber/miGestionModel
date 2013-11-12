package com.migestion.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.migestion.i18n.I18nLocale;

/**
 * Detalle de forma de pago sobre un pago.
 * 
 * Cuando se realiza un pago el monto puede entregarse de varias
 * maneras (formas de pago):
 *   - efectivo
 *   - transferencia
 *   - cheque
 *   - etc
 *   
 * Este detalle representa el monto pagado con determinada forma de pago.
 * De esta manera podemos armar un pago x $1000 pagados con varios formas
 * de pago, tendría estos detalles:
 * 
 *   - $200 por nota de crédito
 *   - $500 en cheque
 *   - $300 en efectivo
 * 
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class DetalleFormaPago extends GenericEntity{

	/**
	 * pago
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="pago_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Pago pago;

	/**
	 * monto pagado con la forma de pago
	 */
	@Column
	@NotNull(message="{detalleFormaPago.monto.required}")
	private Float monto;

	/**
	 * forma de pago
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{detalleFormaPago.formaPago.required}")
	public abstract FormaPago getFormaPago();

	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	

	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)//, generator = "SEQ_DETALLEPAGO")
	@Column
	private Long oid;


	/**
	 * movimiento de cuenta
	 */
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="movimiento_oid", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)	
	private MovimientoCuenta movimiento;
	

	public DetalleFormaPago(){
		
		setMovimiento( createMovimiento() );
	}
	
	
	
	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}


	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @param pago the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}


	/**
	 * @return the monto
	 */
	public Float getMonto() {
		return monto;
	}


	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Float monto) {
		this.monto = monto;

		//si tengo el movimiento, le actualizo el monto.
		if(monto>0)
			getMovimiento().setHaber( monto );			
		else
			getMovimiento().setDebe( monto*(-1) );
		
	}


	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}


	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String toString(I18nLocale i18n){
		return i18n.message( getFormaPago().getNombre() );
	}


	/**
	 * @return the movimiento
	 */
	public MovimientoCuenta getMovimiento() {
		return movimiento;
	}


	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(MovimientoCuenta movimiento) {
		this.movimiento = movimiento;
	}	

	public abstract MovimientoCuenta createMovimiento();
	
	public void buildMovimiento( ConceptoMovimiento concepto, Float monto ){
		
		if(monto>0)
			this.movimiento.setHaber( monto );			
		else
			this.movimiento.setDebe( monto*(-1) );
		
		this.movimiento.setConcepto( concepto );
		this.movimiento.setFechaHora( new Date() );
        
	}
	
}