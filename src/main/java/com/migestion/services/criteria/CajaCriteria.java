package com.migestion.services.criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.migestion.model.EstadoCaja;

/**
 * Criterio de búsqueda para cajas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 30/10/2013
 *
 */
public class CajaCriteria extends Criteria{

	/**
	 * numero
	 */
	private String numero;

	/**
	 * fecha
	 */
	private Date fecha;

	/**
	 * estados posibles.
	 */
	private Collection<EstadoCaja> estados;

	/**
	 * estados a excluir
	 */
	private Collection<EstadoCaja> estadosExcluir;

	public CajaCriteria(){
		super();
		estadosExcluir = new ArrayList<EstadoCaja>();
		estados = new ArrayList<EstadoCaja>();

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
	 * @return the estadosExcluir
	 */
	public Collection<EstadoCaja> getEstadosExcluir() {
		return estadosExcluir;
	}

	/**
	 * @param estadosExcluir the estadosExcluir to set
	 */
	public void setEstadosExcluir(Collection<EstadoCaja> estadosExcluir) {
		this.estadosExcluir = estadosExcluir;
	}
	
	public void addEstadoExcluir(EstadoCaja estado){
		
		this.estadosExcluir.add(estado);
	}

	/**
	 * @return the estados
	 */
	public Collection<EstadoCaja> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(Collection<EstadoCaja> estados) {
		this.estados = estados;
	}

	public void orderByNumero(String type){
		
		this.addOrder("numero", type);
	}

	
	public void orderByFecha(String type){
		
		this.addOrder("fecha", type);
	}
	
}