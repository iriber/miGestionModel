package com.migestion.services.criteria;

import java.util.ArrayList;
import java.util.Collection;

import com.migestion.model.Cliente;
import com.migestion.model.EstadoVenta;

/**
 * Criterio de búsqueda para ventas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 17/10/2013
 *
 */
public class VentaCriteria extends OperacionCriteria{
	
	/**
	 * estados posibles para la venta.
	 */
	private Collection<EstadoVenta> estados;

	/**
	 * estados de venta a excluir
	 */
	private Collection<EstadoVenta> estadosExcluir;

	/**
	 * cliente
	 */
	private Cliente cliente;
	

	
	public VentaCriteria(){
		
		super();
		
		estadosExcluir = new ArrayList<EstadoVenta>();
		
		estados = new ArrayList<EstadoVenta>();
	}
	
	/**
	 * @return the estadosExcluir
	 */
	public Collection<EstadoVenta> getEstadosExcluir() {
		return estadosExcluir;
	}

	/**
	 * @param estadosExcluir the estadosExcluir to set
	 */
	public void setEstadosExcluir(Collection<EstadoVenta> estadosExcluir) {
		this.estadosExcluir = estadosExcluir;
	}
	
	public void addEstadoExcluir(EstadoVenta estado){
		
		this.estadosExcluir.add(estado);
	}

	/**
	 * @return the estados
	 */
	public Collection<EstadoVenta> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(Collection<EstadoVenta> estados) {
		this.estados = estados;
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