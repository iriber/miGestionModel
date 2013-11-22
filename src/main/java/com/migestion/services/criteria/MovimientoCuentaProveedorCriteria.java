package com.migestion.services.criteria;

import com.migestion.model.Proveedor;

/**
 * Criterio de búsqueda para movimientos de cuentas 
 * corrientes de proveedores.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 22/11/2013
 *
 */
public class MovimientoCuentaProveedorCriteria extends MovimientoCuentaCriteria{

	private Proveedor proveedor ;

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


}