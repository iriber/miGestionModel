package com.migestion.services.criteria;

/**
 * Criterio de búsqueda para proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/11/2013
 *
 */
public class ProveedorCriteria extends PersonaCriteria{


	private String cuit;

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
}