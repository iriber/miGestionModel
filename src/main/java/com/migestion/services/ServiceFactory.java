package com.migestion.services;

import com.migestion.services.impl.BalanceServiceImpl;
import com.migestion.services.impl.CajaServiceImpl;
import com.migestion.services.impl.CategoriaProductoServiceImpl;
import com.migestion.services.impl.ClienteServiceImpl;
import com.migestion.services.impl.ConceptoMovimientoServiceImpl;
import com.migestion.services.impl.CuentaBancariaServiceImpl;
import com.migestion.services.impl.MovimientoCajaServiceImpl;
import com.migestion.services.impl.PagoServiceImpl;
import com.migestion.services.impl.ProductoServiceImpl;
import com.migestion.services.impl.SucursalServiceImpl;
import com.migestion.services.impl.VendedorServiceImpl;
import com.migestion.services.impl.VentaServiceImpl;

/**
 * Factory de servicios
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class ServiceFactory {

	/**
	 * servicio para categorías de producto
	 * @return
	 */
	public static ICategoriaProductoService getCategoriaProductoService(){
		return CategoriaProductoServiceImpl.getInstance();
	}

	/**
	 * servicios para productos.
	 * @return
	 */
	public static IProductoService getProductoService(){
		return ProductoServiceImpl.getInstance();
	}
	
	/**
	 * servicios para clientes.
	 * @return
	 */
	public static IClienteService getClienteService(){
		return ClienteServiceImpl.getInstance();
	}
	
	/**
	 * servicios para vendedores.
	 * @return
	 */
	public static IVendedorService getVendedorService(){
		return VendedorServiceImpl.getInstance();
	}
	

	/**
	 * servicios para ventas.
	 * @return
	 */
	public static IVentaService getVentaService(){
		return VentaServiceImpl.getInstance();
	}
	

	/**
	 * servicios para sucursales.
	 * @return
	 */
	public static ISucursalService getSucursalService(){
		return SucursalServiceImpl.getInstance();
	}
	
	/**
	 * servicios para cuentas bancarias.
	 * @return
	 */
	public static ICuentaBancariaService getCuentaBancariaService(){
		return CuentaBancariaServiceImpl.getInstance();
	}
	
	/**
	 * servicios para pagos.
	 * @return
	 */
	public static IPagoService getPagoService(){
		return PagoServiceImpl.getInstance();
	}

	/**
	 * servicios para cajas.
	 * @return
	 */
	public static ICajaService getCajaService(){
		return CajaServiceImpl.getInstance();
	}
	
	/**
	 * servicios para movimientos de cajas.
	 * @return
	 */
	public static IMovimientoCajaService getMovimientoCajaService(){
		return MovimientoCajaServiceImpl.getInstance();
	}

	/**
	 * servicios para conceptos de cajas.
	 * @return
	 */
	public static IConceptoMovimientoService getConceptoCajaService(){
		return ConceptoMovimientoServiceImpl.getInstance();
	}
	
	/**
	 * servicios para balances.
	 * @return
	 */
	public static IBalanceService getBalanceService(){
		return BalanceServiceImpl.getInstance();
	}	
}