package com.migestion.services.criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.migestion.model.Cliente;
import com.migestion.model.EstadoPago;
import com.migestion.model.Operacion;

/**
 * Criterio de búsqueda para pagos.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public class PagoCriteria extends Criteria{

	/**
	 * fecha de pago desde
	 */
	private Date fechaDesde;

	/**
	 * fecha de pago hasta
	 */
	private Date fechaHasta;

	/**
	 * cliente
	 */
	private Cliente cliente;
	
	/**
	 * estados posibles para el pago.
	 */
	private Collection<EstadoPago> estados;

	/**
	 * estados de pago a excluir
	 */
	private Collection<EstadoPago> estadosExcluir;

	/**
	 * operación involucrada en el pago.
	 */
	private Operacion operacion;
	
	public PagoCriteria(){
		
		estadosExcluir = new ArrayList<EstadoPago>();
		
		estados = new ArrayList<EstadoPago>();
	}

	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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

	public void orderByFecha(String type){
		
		this.addOrder("fecha", type);
	}

	public void orderByCodigo(String type){
		
		this.addOrder("oid", type);
	}
	
	/**
	 * @return the estadosExcluir
	 */
	public Collection<EstadoPago> getEstadosExcluir() {
		return estadosExcluir;
	}

	/**
	 * @param estadosExcluir the estadosExcluir to set
	 */
	public void setEstadosExcluir(Collection<EstadoPago> estadosExcluir) {
		this.estadosExcluir = estadosExcluir;
	}
	
	public void addEstadoExcluir(EstadoPago estado){
		
		this.estadosExcluir.add(estado);
	}

	/**
	 * @return the estados
	 */
	public Collection<EstadoPago> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(Collection<EstadoPago> estados) {
		this.estados = estados;
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