package com.migestion.services;

import com.migestion.services.impl.BalanceServiceImpl;
import com.migestion.services.impl.CajaServiceImpl;
import com.migestion.services.impl.CategoriaProductoServiceImpl;
import com.migestion.services.impl.ChequeServiceImpl;
import com.migestion.services.impl.ClienteServiceImpl;
import com.migestion.services.impl.ConceptoMovimientoServiceImpl;
import com.migestion.services.impl.CuentaBancariaServiceImpl;
import com.migestion.services.impl.MovimientoCajaServiceImpl;
import com.migestion.services.impl.NotaCreditoServiceImpl;
import com.migestion.services.impl.PagoServiceImpl;
import com.migestion.services.impl.ProductoServiceImpl;
import com.migestion.services.impl.SucursalServiceImpl;
import com.migestion.services.impl.VendedorServiceImpl;
import com.migestion.services.impl.VentaServiceImpl;

/**
 * Factory de servicio
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
	 * servicio para productos.
	 * @return
	 */
	public static IProductoService getProductoService(){
		return ProductoServiceImpl.getInstance();
	}
	
	/**
	 * servicio para clientes.
	 * @return
	 */
	public static IClienteService getClienteService(){
		return ClienteServiceImpl.getInstance();
	}
	
	/**
	 * servicio para vendedores.
	 * @return
	 */
	public static IVendedorService getVendedorService(){
		return VendedorServiceImpl.getInstance();
	}
	

	/**
	 * servicio para ventas.
	 * @return
	 */
	public static IVentaService getVentaService(){
		return VentaServiceImpl.getInstance();
	}
	

	/**
	 * servicio para sucursales.
	 * @return
	 */
	public static ISucursalService getSucursalService(){
		return SucursalServiceImpl.getInstance();
	}
	
	/**
	 * servicio para cuentas bancarias.
	 * @return
	 */
	public static ICuentaBancariaService getCuentaBancariaService(){
		return CuentaBancariaServiceImpl.getInstance();
	}
	
	/**
	 * servicio para pagos.
	 * @return
	 */
	public static IPagoService getPagoService(){
		return PagoServiceImpl.getInstance();
	}

	/**
	 * servicio para cajas.
	 * @return
	 */
	public static ICajaService getCajaService(){
		return CajaServiceImpl.getInstance();
	}
	
	/**
	 * servicio para movimientos de cajas.
	 * @return
	 */
	public static IMovimientoCajaService getMovimientoCajaService(){
		return MovimientoCajaServiceImpl.getInstance();
	}

	/**
	 * servicio para conceptos de cajas.
	 * @return
	 */
	public static IConceptoMovimientoService getConceptoCajaService(){
		return ConceptoMovimientoServiceImpl.getInstance();
	}
	
	/**
	 * servicio para balances.
	 * @return
	 */
	public static IBalanceService getBalanceService(){
		return BalanceServiceImpl.getInstance();
	}
	
	/**
	 * servicio para notas de crédito.
	 * @return
	 */
	public static INotaCreditoService getNotaCreditoService(){
		return NotaCreditoServiceImpl.getInstance();
	}
	
	/**
	 * servicio para cheques.
	 * @return
	 */
	public static IChequeService getChequeService(){
		return ChequeServiceImpl.getInstance();
	}
}