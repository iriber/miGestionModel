package com.migestion.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Operación
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @param <Detalle>
 * @since 17/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Operacion extends GenericEntity{

	
	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)//, generator = "SEQ_DETALLEPAGO")
	//@SequenceGenerator(name = "SEQ_DETALLEPAGO", sequenceName = "SEQ_DETALLEPAGO", allocationSize = 1)
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
	 * monto total de la operación
	 */
	@Column
	private Float monto;
	
	//@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="operacion")
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<DetalleOperacion> detalles;

	

	public Operacion(){
		detalles = new ArrayList<DetalleOperacion>();
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

	/**
	 * @return the vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
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

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * se paga la operación con el monto indicado. 
	 * Usará de ese monto lo que necesite.
	 * Si no alcanza, usa lo que hay.
	 * @param monto
	 * @return detalle de pago
	 */
	public abstract DetallePago pagate(Float monto);

	/**
	 * se paga la operación en su totalidad. 
	 * @return detalle de pago
	 */
	public abstract DetallePago pagate();

	/**
	 * se anula el pago sobre la operación
	 * @param detalle
	 */
	public abstract void anularPago(DetallePago detalle);
	
	/**
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
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

	/**
	 * @return the detalles
	 */
	public List<DetalleOperacion> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List<DetalleOperacion> detalles) {
		this.detalles = detalles;
	}

	public void addDetalle(DetalleOperacion detalle){
		detalles.add(detalle);
	}

	public abstract String getDescripcion();
}