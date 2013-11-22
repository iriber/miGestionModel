package com.migestion.services.criteria;

import java.util.ArrayList;
import java.util.Collection;

import com.migestion.model.EstadoOrdenCompra;
import com.migestion.model.Proveedor;

/**
 * Criterio de búsqueda para órdenes de compra.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 21/11/2013
 *
 */
public class OrdenCompraCriteria extends OperacionCriteria{
	
	/**
	 * estados posibles para la orden.
	 */
	private Collection<EstadoOrdenCompra> estados;

	/**
	 * estados a excluir
	 */
	private Collection<EstadoOrdenCompra> estadosExcluir;

	/**
	 * proveedor
	 */
	private Proveedor proveedor;
	

	
	public OrdenCompraCriteria(){
		
		super();
		
		estadosExcluir = new ArrayList<EstadoOrdenCompra>();
		
		estados = new ArrayList<EstadoOrdenCompra>();
	}
	
	/**
	 * @return the estadosExcluir
	 */
	public Collection<EstadoOrdenCompra> getEstadosExcluir() {
		return estadosExcluir;
	}

	/**
	 * @param estadosExcluir the estadosExcluir to set
	 */
	public void setEstadosExcluir(Collection<EstadoOrdenCompra> estadosExcluir) {
		this.estadosExcluir = estadosExcluir;
	}
	
	public void addEstadoExcluir(EstadoOrdenCompra estado){
		
		this.estadosExcluir.add(estado);
	}

	/**
	 * @return the estados
	 */
	public Collection<EstadoOrdenCompra> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(Collection<EstadoOrdenCompra> estados) {
		this.estados = estados;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	


}