package com.migestion.model;


import java.util.Date;

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
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Movimiento de cuenta (caja, banco, etc)
 * 
 * Representa un movimiento de cuenta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class MovimientoCuenta extends GenericEntity{

	/**
	 * identificador de la entity
	 */
	@TableGenerator(table = "hibernate_sequences", name = "MiGestionIdTable", 
		    allocationSize = 1, initialValue = 0, pkColumnName = "sequence_name", 
		    valueColumnName = "sequence_next_hi_value", pkColumnValue = "MovimientoCuenta")
	@GeneratedValue(strategy = GenerationType.TABLE,generator="MiGestionIdTable")
	@Id
	private Long oid;

	
	/**
	 * fecha y hora
	 */
	@Column
	@NotNull(message="{movimientoCaja.fechaHora.required}")
	private Date fechaHora;
	
	/**
	 * debe
	 */
	@Column
	@NotNull(message="{movimientoCaja.debe.required}")
	private Float debe;

	/**
	 * haber
	 */
	@Column
	@NotNull(message="{movimientoCaja.haber.required}")
	private Float haber;

	/**
	 * saldo
	 */
	@Column
	@NotNull(message="{movimientoCaja.saldo.required}")
	private Float saldo;

	/**
	 * concepto
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="concepto_oid", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	@NotNull(message="{movimientoCaja.concepto.required}")
	private ConceptoMovimiento concepto;
	
	/**
	 * descripción del movimiento
	 * para indicar algún detalle adicional.	
	 */
	@Column
	private String descripcion;

	
	public MovimientoCuenta(){
		
		this.debe = 0F;
		this.haber = 0F;
	}
	

	/**
	 * @return the debe
	 */
	public Float getDebe() {
		return debe;
	}


	/**
	 * @param debe the debe to set
	 */
	public void setDebe(Float debe) {
		this.debe = debe;
	}


	/**
	 * @return the haber
	 */
	public Float getHaber() {
		return haber;
	}


	/**
	 * @param haber the haber to set
	 */
	public void setHaber(Float haber) {
		this.haber = haber;
	}


	/**
	 * @return the saldo
	 */
	public Float getSaldo() {
		return saldo;
	}


	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}


	/**
	 * @return the conceptoCaja
	 */
	public ConceptoMovimiento getConcepto() {
		return concepto;
	}


	/**
	 * @param conceptoCaja the conceptoCaja to set
	 */
	public void setConcepto(ConceptoMovimiento concepto) {
		this.concepto = concepto;
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
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}


	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
		

	public void calcularSaldos(){
		//calculamos el saldo en base al movimiento y el saldo actual de la caja
		Float debe = (getDebe()!=null)?getDebe():0F;
		Float haber = (getHaber()!=null)?getHaber():0F;
		
		Float saldo = getSaldoCuenta() - debe + haber;
				
		setSaldo(saldo);
		setDebe(debe);
		setHaber(haber);
		
		setSaldoCuenta(saldo);
		
	}
	
	
	public abstract void setSaldoCuenta(Float monto);
	
	public abstract Float getSaldoCuenta();


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}