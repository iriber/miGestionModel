package com.migestion.model;


import java.text.SimpleDateFormat;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.migestion.i18n.I18nLocale;

/**
 * Representa la caja diaria.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */

@Entity
@Table(name="caja")
public class Caja extends GenericEntity{

	/**
	 * fecha
	 */
	@Column
	@NotNull(message="{caja.fecha.required}")
	private Date fecha;
	
	/**
	 * saldo inicial 
	 */
	@Column
	private Float saldoInicial;
	
	/**
	 * saldo
	 */
	@Column
	private Float saldo;

	/**
	 * monto sobre la operación
	 */
	@NotNull(message="{caja.cajero.required}")
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="cajero_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Vendedor cajero;

	/**
	 * número de la caja
	 */
	@Column
	@NotNull(message="{caja.numero.required}")
	private String numero;


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
	 * sucursal
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="sucursal_oid")
	@NotFound(action=NotFoundAction.IGNORE)	
	private Sucursal sucursal;

	/**
	 * movimientos de caja.
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="caja",fetch = FetchType.EAGER)
	private Set<MovimientoCaja> movimientos;
	
	
	/**
	 * estado de la caja
	 */
	@Enumerated( value=EnumType.STRING )
	@NotNull(message="{caja.estado.required}")
	private EstadoCaja estadoCaja;

	
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

	public Caja(){
		movimientos = new HashSet<MovimientoCaja>();
	}


	public String toString(){
		return  new SimpleDateFormat("dd/MM/yyyy").format( getFecha() )  + " -  Nro: " + getNumero()   + " -  Saldo: " + getSaldo()   ;
	}
	
	public String toString(I18nLocale i18n){
		return  new SimpleDateFormat("dd/MM/yyyy").format( getFecha() ) + " - " +  i18n.message("caja.numero") + ": " + getNumero()  + " - " +  i18n.message("caja.saldo") + ": " + getSaldo();
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
	 * @return the saldoInicial
	 */
	public Float getSaldoInicial() {
		return saldoInicial;
	}


	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(Float saldoInicial) {
		this.saldoInicial = saldoInicial;
		this.saldo = saldoInicial;
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
	 * @return the cajero
	 */
	public Vendedor getCajero() {
		return cajero;
	}


	/**
	 * @param cajero the cajero to set
	 */
	public void setCajero(Vendedor cajero) {
		this.cajero = cajero;
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
	 * @return the movimientos
	 */
	public List<MovimientoCaja> getMovimientosCaja() {
		List<MovimientoCaja> movimientos = new ArrayList<MovimientoCaja>();
		movimientos.addAll(this.movimientos);
		return movimientos;
	}


	/**
	 * @param movimientos the movimientos to set
	 */
	public void setMovimientosCaja(Set<MovimientoCaja> movimientos) {
		
		this.movimientos = new HashSet<MovimientoCaja>();
		
		for (MovimientoCaja movimiento : movimientos) {
			this.movimientos.add( movimiento );
		}
		
	}
	
	/**
	 * se agrega un movimiento de caja.
	 * @param movimiento
	 */
	public void addMovimientoCaja( MovimientoCaja movimiento){
		movimiento.setCaja(this);
		this.movimientos.add(movimiento);
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


	/**
	 * @return the estadoCaja
	 */
	public EstadoCaja getEstadoCaja() {
		return estadoCaja;
	}


	/**
	 * @param estadoCaja the estadoCaja to set
	 */
	public void setEstadoCaja(EstadoCaja estadoCaja) {
		this.estadoCaja = estadoCaja;
	}

}