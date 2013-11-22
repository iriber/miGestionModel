package com.migestion.model;


import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Venta
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */

@Entity
@Table(name="venta")
public class Venta extends Operacion{

	/**
	 * estado de la venta
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{venta.estado.required}")
	private EstadoVenta estadoVenta;

	/**
	 * monto pagado
	 */
	@Column
	private Float montoPagado;
	
	/**
	 * monto debe
	 */
	@Column
	private Float montoDebe;
	
	/**
	 * cliente
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cliente_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{operacion.cliente.required}")
	private Cliente cliente;
	

	public Venta(){
		super();
		this.montoPagado = 0F;
		this.montoDebe = 0F;
	}


	/**
	 * @return the montoPagado
	 */
	public Float getMontoPagado() {
		return montoPagado;
	}


	/**
	 * @param montoPagado the montoPagado to set
	 */
	public void setMontoPagado(Float montoPagado) {
		this.montoPagado = montoPagado;
	}


	/**
	 * @return the montoDebe
	 */
	public Float getMontoDebe() {
		return montoDebe;
	}


	/**
	 * @param montoDebe the montoDebe to set
	 */
	public void setMontoDebe(Float montoDebe) {
		
		this.montoDebe = montoDebe;
		
		//cuando el monto debe es 0F la pasamos a estado PAGADA
		
		//cuando el monto es igual al total de la venta queda IMPAGA
		
		//cuando el monto es > 0 pero < al total (hay algo pagado), queda PAGADA_PARCIALMENTE
		
		if( this.montoDebe!=null && this.montoDebe== 0){
		
			setEstadoVenta(EstadoVenta.PAGADA);
		
		}else if( this.montoPagado!=null && this.montoPagado > 0 ){
			
			setEstadoVenta(EstadoVenta.PAGADA_PARCIALMENTE);
			
		}else{
			
			setEstadoVenta(EstadoVenta.IMPAGA);
			
		}
		
	}


	public void addDetalle(DetalleOperacion detalle){
		((DetalleVenta)detalle).setVenta(this);
		super.addDetalle(detalle);
	}


	/**
	 * @return the estadoVenta
	 */
	public EstadoVenta getEstadoVenta() {
		return estadoVenta;
	}


	/**
	 * @param estadoVenta the estadoVenta to set
	 */
	public void setEstadoVenta(EstadoVenta estadoVenta) {
		this.estadoVenta = estadoVenta;
	}


	@Override
	public DetallePago pagate(Float monto) {
		
		Float montoPagar = 0F;
		if( getMontoDebe() > monto ){
			
			//usamos todo el monto y la venta queda pagada parcialmente
			montoPagar = monto;
			setMontoPagado( montoPagar + getMontoPagado() );
			setMontoDebe( getMontoDebe() - montoPagar );
			
			
		}else{
			
			//usamos lo que falta pagar ya que el monto es mayor a lo que se debe
			//entonces la venta queda pagada
			montoPagar = getMontoDebe();
			setMontoPagado( montoPagar + getMontoPagado() );
			setMontoDebe( 0F );
			
			
		}
		
		DetallePagoVenta detalle = new DetallePagoVenta();
		detalle.setOperacion( this );
		detalle.setMonto(montoPagar);
		
		return detalle;
	}


	@Override
	public DetallePago pagate() {
		
		return this.pagate(getMontoDebe());
		
	}


	@Override
	public String getDescripcion() {
		
		return "operacion.venta";
	}


	@Override
	public void anularPago(DetallePago detalle) {
		
	     this.setMontoPagado( getMontoPagado() - detalle.getMonto() );
	     this.setMontoDebe( getMontoDebe() + detalle.getMonto() );
	     
	}
	
	public String toString(){
		
		return this.getOid() + " - " + new SimpleDateFormat("dd/MM/yyyy").format( getFecha() ) ;
	}


	@Override
	public Boolean podesPagarte() {
		
		return getEstadoVenta().podesPagarte();
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
}