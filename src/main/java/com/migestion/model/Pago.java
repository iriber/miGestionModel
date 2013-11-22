package com.migestion.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Pago
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract public class Pago extends GenericEntity{

	
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
	 * estado del pago
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{pago.estado.required}")
	private EstadoPago estadoPago;
	

	/**
	 * sucursal
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="sucursal_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Sucursal sucursal;

	/**
	 * detalles del pago. sería el detalle de las operaciones que se
	 * pagan, indicando el monto sobre cada una de ellas.
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pago",fetch = FetchType.EAGER)
	private Set<DetallePago> detallesPago;
	

	/**
	 * detalles de las formas de pago. sería el detalle de los montos pagados con
	 * cada forma de pago.
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pago",fetch = FetchType.EAGER)
	private Set<DetalleFormaPago> detallesFormaPago;
		
	/**
	 * identificador de la entity
	 */
	@TableGenerator(table = "hibernate_sequences", name = "MiGestionIdTable", 
		    allocationSize = 1, initialValue = 0, pkColumnName = "sequence_name", 
		    valueColumnName = "sequence_next_hi_value", pkColumnValue = "Pago")
	@GeneratedValue(strategy = GenerationType.TABLE,generator="MiGestionIdTable")
	@Id
	private Long oid;


	public Pago(){
		detallesPago = new HashSet<DetallePago>();
		detallesFormaPago = new HashSet<DetalleFormaPago>();
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
	 * @return the estadoPago
	 */
	public EstadoPago getEstadoPago() {
		return estadoPago;
	}


	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(EstadoPago estadoPago) {
		this.estadoPago = estadoPago;
	}


	/**
	 * @return the detalles
	 */
	public List<DetallePago> getDetallesPago() {
		List<DetallePago> detalles = new ArrayList<DetallePago>();
		detalles.addAll(detallesPago);
		return detalles;
	}


	/**
	 * @param detalles the detalles to set
	 */
	public void setDetallesPago(Set<DetallePago> detallesPago) {
		
		this.detallesPago = new HashSet<DetallePago>();
		
		for (DetallePago detalle : detallesPago) {
			this.detallesPago.add( detalle );
		}
		
	}
	
	/**
	 * se agrega un detalle al pago.
	 * @param detalle
	 */
	public void addDetalle( DetallePago detalle){
		detalle.setPago(this);
		this.detallesPago.add(detalle);
	}

	/**
	 * @return the detalles
	 */
	public List<DetalleFormaPago> getDetallesFormaPago() {
		List<DetalleFormaPago> detalles = new ArrayList<DetalleFormaPago>();
		detalles.addAll(detallesFormaPago);
		return detalles;
	}


	/**
	 * @param detalles the detalles to set
	 */
	public void setDetallesFormaPago(Set<DetalleFormaPago> detallesFormaPago) {
		this.detallesFormaPago = new HashSet<DetalleFormaPago>();
		
		for (DetalleFormaPago detalle : detallesFormaPago) {
			this.detallesFormaPago.add( detalle );
		}
	}
	
	/**
	 * se agrega un detalle al pago.
	 * @param detalle
	 */
	public void addDetalle( DetalleFormaPago detalle){
		detalle.setPago(this);
		this.detallesFormaPago.add(detalle);
	}

	/**
	 * dado el monto, lo distribuye en las operaciones.
	 * 
	 * @param operaciones operaciones a pagar.
	 */
	public void pagar( Float monto, List<Operacion> operaciones ){
		
		int index = 0;
		int operacionesSize = operaciones.size();
		Boolean seguirPagando = (monto > 0) && (index < operacionesSize);
		
		while (seguirPagando) {
			
			DetallePago detallePago = operaciones.get(index).pagate( monto );
			
			this.addDetalle(detallePago);
			
			monto -= detallePago.getMonto();
			
			index++;
			seguirPagando = (monto > 0) && (index < operacionesSize);
		}
		
		recalcularMonto();
	}

	
	/**
	 * se paga una operación en su totalidad
	 * 
	 * @param operaciones operación a pagar.
	 */
	public void pagar( Operacion operacion ){
		
			
		this.addDetalle( operacion.pagate());
			
		recalcularMonto();
		
	}
	/**
	 * dado los detalles del pago, calculo el monto total del pago.
	 * 
	 * @param detalles
	 */
	public void recalcularMonto(){
			
		Float monto = 0F;

		for (DetallePago detalle : getDetallesPago()) {
			monto += detalle.getMonto();
		}
		
		setMonto(monto);
		
	}


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


	public void anulate(){
		
		setEstadoPago(EstadoPago.ANULADO);
		
		//revierto los detalles de pago sobre las operaciones.
		for (DetallePago detalle : getDetallesPago()) {
			detalle.getOperacion().anularPago(detalle);
		}
	}
	
	/**
	 * opeaciones afectadas por el pago.
	 * @return
	 */
	public List<Operacion> getOperaciones(){
		List<Operacion> operaciones = new ArrayList<Operacion>();
		for (DetallePago detalle : getDetallesPago()) {
			operaciones.add( detalle.getOperacion() );
		}
		return operaciones;
		
	}
	
	/**
	 * retorna el monto pagado sobre la operación
	 * @param operacion
	 * @return
	 */
	public Float getMontoPagado(Operacion operacion){
		
		Float monto = 0F;
		
		//revierto los detalles de pago sobre las operaciones.
		for (DetallePago detalle : getDetallesPago()) {
			if( detalle.getOperacion().equals( operacion) );
				monto = detalle.getMonto();
		}
		
		return monto;
	}

}