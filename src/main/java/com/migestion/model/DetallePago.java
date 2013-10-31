package com.migestion.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * Detalle de un pago.
 * 
 * Representa un detalle del pago sobre una de las operaciones que cancela.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class DetallePago extends GenericEntity{

	
	/**
	 * pago a ventas
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="pago_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Pago pago;
	
	/**
	 * monto sobre la operación
	 */
	@Column
	@NotNull(message="{detallePago.monto.required}")
	private Float monto;

	/**
	 * operación
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="operacion_oid", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)	
	private Operacion operacion;
	
	
	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)//, generator = "SEQ_DETALLEPAGO")
	//@SequenceGenerator(name = "SEQ_DETALLEPAGO", sequenceName = "SEQ_DETALLEPAGO", allocationSize = 1)
	private Long oid;
		

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
	 * @return the operacion
	 */
	public Operacion getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

}