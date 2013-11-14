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

/**
 * Gasto
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 12/11/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Gasto extends GenericEntity{

	/**
	 * vendedor
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="vendedor_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Vendedor vendedor;
	
	/**
	 * observaciones
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	
	/**
	 * fecha
	 */
	@Column
	private Date fecha;

	/**
	 * monto pagado
	 */
	@Column
	private Float monto;

	/**
	 * porcentaje de iva
	 */
	@Column
	private Float iva;
	
	/**
	 * retención IIBB
	 */
	@Column
	private Float rentencionIIBB;
	
	
	/**
	 * sucursal
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="sucursal_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Sucursal sucursal;

	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private Long oid;

	/**
	 * movimiento de cuenta
	 */
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="movimiento_oid", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)	
	private MovimientoCuenta movimiento;

	/**
	 * tipo de factura
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{gasto.tipoFactura.required}")
	private TipoFactura tipoFactura;
	
	public Gasto(){
		
		this.movimiento = createMovimiento();
		this.movimiento.setFechaHora( new Date() );
		this.iva = 21F;
		this.rentencionIIBB = 0F;
		this.tipoFactura = TipoFactura.C;
	}

	public abstract MovimientoCuenta createMovimiento();
	
	/**
	 * forma de pago
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{gasto.formaPago.required}")
	public abstract FormaPago getFormaPago();

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

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
		this.movimiento.setDebe( monto );
		this.movimiento.setHaber( 0F );
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public MovimientoCuenta getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(MovimientoCuenta movimiento) {
		this.movimiento = movimiento;
	}

	public MovimientoCuenta buildContraMovimiento(ConceptoMovimiento concepto) {

		MovimientoCuenta contraMovimiento = createMovimiento();
		contraMovimiento.setHaber( this.movimiento.getDebe() );			
		contraMovimiento.setDebe( this.movimiento.getHaber() );
		contraMovimiento.setConcepto( concepto );
		contraMovimiento.setFechaHora( new Date() );
        
		return contraMovimiento;
	}

	public void setConcepto( ConceptoMovimiento concepto ){
		
		this.movimiento.setConcepto( concepto );
        
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getRentencionIIBB() {
		return rentencionIIBB;
	}

	public void setRentencionIIBB(Float rentencionIIBB) {
		this.rentencionIIBB = rentencionIIBB;
	}

	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	

}