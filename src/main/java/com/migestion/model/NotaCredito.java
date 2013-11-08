package com.migestion.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.migestion.i18n.I18nLocale;

/**
 * Representa una nota de crédito.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 07/11/2013
 *
 */

@Entity
@Table(name="nota_credito")
public class NotaCredito extends GenericEntity{

	/**
	 * fecha de vencimiento
	 */
	@Column
	private Date fechaVencimiento;

	/**
	 * fecha
	 */
	@Column
	private Date fecha;
	

	/**
	 * número
	 */
	@Column
	private String numero;
	
	/**
	 * monto sobre la operación
	 */
	@Column
	@NotNull(message="{detallePago.monto.required}")
	private Float monto;

	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	

	/**
	 * identificador de la entity
	 */
	@Id @GeneratedValue
	@Column
	private Long oid;

	/**
	 * cliente
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cliente_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{operacion.cliente.required}")
	private Cliente cliente;
	
	/**
	 * vendedor
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="vendedor_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Vendedor vendedor;

	/**
	 * sucursal
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="sucursal_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{operacion.sucursal.required}")
	private Sucursal sucursal;
	

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Sucursal getSucursal() {
		return sucursal;
	}


	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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

	public NotaCredito(){
		
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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

	public String toString(){
		return " Nro: " + getNumero() + " F.Vencimiento: " + new SimpleDateFormat("dd/MM/yyyy").format( getFechaVencimiento() );
	}
	
	public String toString(I18nLocale i18n){
		return i18n.message("notaCredito.numero") + ": " + getNumero() + " " + i18n.message("notaCredito.fechaVencimiento") + ": " +  new SimpleDateFormat("dd/MM/yyyy").format( getFechaVencimiento() );
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}