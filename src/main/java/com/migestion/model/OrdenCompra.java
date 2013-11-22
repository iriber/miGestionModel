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
 * Orden de compra a proveedor
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */

@Entity
@Table(name="orden_compra")
public class OrdenCompra extends Operacion{

	/**
	 * estado de la orden de compra
	 * (respecto al pago).
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{ordenCompra.estado.required}")
	private EstadoOrdenCompra estadoOrdenCompra;

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
	 * proveedor
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="proveedor_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	@NotNull(message="{ordenCompra.proveedor.required}")
	private Proveedor proveedor;

	
	public OrdenCompra(){
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
		
			setEstadoOrdenCompra(EstadoOrdenCompra.PAGADA);
		
		}else if( this.montoPagado!=null && this.montoPagado > 0 ){
			
			setEstadoOrdenCompra(EstadoOrdenCompra.PAGADA_PARCIALMENTE);
			
		}else{
			
			setEstadoOrdenCompra(EstadoOrdenCompra.IMPAGA);
			
		}
		
	}


	public void addDetalle(DetalleOperacion detalle){
		((DetalleOrdenCompra)detalle).setOrdenCompra(this);
		super.addDetalle(detalle);
	}


	/**
	 * @return the estadoOrdenCompra
	 */
	public EstadoOrdenCompra getEstadoOrdenCompra() {
		return estadoOrdenCompra;
	}


	/**
	 * @param estadoOrdenCompra the estadoOrdenCompra to set
	 */
	public void setEstadoOrdenCompra(EstadoOrdenCompra estadoOrdenCompra) {
		this.estadoOrdenCompra = estadoOrdenCompra;
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
		
		DetallePagoOrdenCompra detalle = new DetallePagoOrdenCompra();
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
		
		return "operacion.ordenCompra";
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
		
		return getEstadoOrdenCompra().podesPagarte();
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}