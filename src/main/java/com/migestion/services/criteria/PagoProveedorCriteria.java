package com.migestion.services.criteria;

import com.migestion.model.Proveedor;

/**
 * Criterio de búsqueda para pagos a proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class PagoProveedorCriteria extends PagoCriteria{

	/**
	 * proveedor
	 */
	private Proveedor proveedor;
	
	
	public PagoProveedorCriteria(){
		
		super();
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


}