package com.migestion.dao;

import com.migestion.dao.impl.BalanceJPADAO;
import com.migestion.dao.impl.CajaJPADAO;
import com.migestion.dao.impl.CategoriaProductoJPADAO;
import com.migestion.dao.impl.ChequeJPADAO;
import com.migestion.dao.impl.ClienteJPADAO;
import com.migestion.dao.impl.ConceptoMovimientoJPADAO;
import com.migestion.dao.impl.CuentaBancariaJPADAO;
import com.migestion.dao.impl.GastoJPADAO;
import com.migestion.dao.impl.MovimientoCajaJPADAO;
import com.migestion.dao.impl.MovimientoChequeJPADAO;
import com.migestion.dao.impl.MovimientoCuentaBancariaJPADAO;
import com.migestion.dao.impl.MovimientoCuentaJPADAO;
import com.migestion.dao.impl.MovimientoNotaCreditoJPADAO;
import com.migestion.dao.impl.NotaCreditoJPADAO;
import com.migestion.dao.impl.PagoJPADAO;
import com.migestion.dao.impl.ProductoJPADAO;
import com.migestion.dao.impl.SucursalJPADAO;
import com.migestion.dao.impl.VendedorJPADAO;
import com.migestion.dao.impl.VentaJPADAO;
import com.migestion.model.Caja;
import com.migestion.model.CategoriaProducto;
import com.migestion.model.Cheque;
import com.migestion.model.Cliente;
import com.migestion.model.ConceptoMovimiento;
import com.migestion.model.CuentaBancaria;
import com.migestion.model.Gasto;
import com.migestion.model.MovimientoCaja;
import com.migestion.model.MovimientoCheque;
import com.migestion.model.MovimientoCuenta;
import com.migestion.model.MovimientoCuentaBancaria;
import com.migestion.model.MovimientoNotaCredito;
import com.migestion.model.NotaCredito;
import com.migestion.model.Pago;
import com.migestion.model.Producto;
import com.migestion.model.Sucursal;
import com.migestion.model.Vendedor;
import com.migestion.model.Venta;
import com.migestion.services.criteria.CajaCriteria;
import com.migestion.services.criteria.CategoriaProductoCriteria;
import com.migestion.services.criteria.ChequeCriteria;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.criteria.ConceptoMovimientoCriteria;
import com.migestion.services.criteria.CuentaBancariaCriteria;
import com.migestion.services.criteria.GastoCriteria;
import com.migestion.services.criteria.MovimientoCajaCriteria;
import com.migestion.services.criteria.MovimientoChequeCriteria;
import com.migestion.services.criteria.MovimientoCuentaBancariaCriteria;
import com.migestion.services.criteria.MovimientoCuentaCriteria;
import com.migestion.services.criteria.MovimientoNotaCreditoCriteria;
import com.migestion.services.criteria.NotaCreditoCriteria;
import com.migestion.services.criteria.PagoCriteria;
import com.migestion.services.criteria.ProductoCriteria;
import com.migestion.services.criteria.SucursalCriteria;
import com.migestion.services.criteria.VendedorCriteria;
import com.migestion.services.criteria.VentaCriteria;

/**
 * Factory de DAOs
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 *
 */
public class DAOFactory {

	/**
	 * dao para productos
	 * @return
	 */
	public static IGenericDAO<Producto, ProductoCriteria> getProductoDAO(){
		return new ProductoJPADAO();
	}

	/**
	 * dao para categorías de producto
	 * @return
	 */
	public static IGenericDAO<CategoriaProducto, CategoriaProductoCriteria> getCategoriaProductoDAO(){
		return new CategoriaProductoJPADAO();
	}

	/**
	 * dao para clientes
	 * @return
	 */
	public static IGenericDAO<Cliente, ClienteCriteria> getClienteDAO(){
		return new ClienteJPADAO();
	}
	
	/**
	 * dao para vendedores
	 * @return
	 */
	public static IGenericDAO<Vendedor, VendedorCriteria> getVendedorDAO(){
		return new VendedorJPADAO();
	}
	

	/**
	 * dao para ventas
	 * @return
	 */
	public static IGenericDAO<Venta, VentaCriteria> getVentaDAO(){
		return new VentaJPADAO();
	}
	
	/**
	 * dao para cuentas bancarias
	 * @return
	 */
	public static IGenericDAO<CuentaBancaria, CuentaBancariaCriteria> getCuentaBancariaDAO(){
		return new CuentaBancariaJPADAO();
	}
	
	/**
	 * dao para sucursales
	 * @return
	 */
	public static IGenericDAO<Sucursal, SucursalCriteria> getSucursalDAO(){
		return new SucursalJPADAO();
	}

	/**
	 * dao para pagos
	 * @return
	 */
	public static IGenericDAO<Pago, PagoCriteria> getPagoDAO(){
		return new PagoJPADAO();
	}

	/**
	 * dao para cajas
	 * @return
	 */
	public static IGenericDAO<Caja, CajaCriteria> getCajaDAO(){
		return new CajaJPADAO();
	}

	/**
	 * dao para movimientos de caja
	 * @return
	 */
	public static IGenericDAO<MovimientoCaja, MovimientoCajaCriteria> getMovimientoCajaDAO(){
		return new MovimientoCajaJPADAO();
	}

	/**
	 * dao para movimientos de cuentas bancarias
	 * @return
	 */
	public static IGenericDAO<MovimientoCuentaBancaria, MovimientoCuentaBancariaCriteria> getMovimientoCuentaBancariaDAO(){
		return new MovimientoCuentaBancariaJPADAO();
	}

	/**
	 * dao para movimientos de cheques
	 * @return
	 */
	public static IGenericDAO<MovimientoCheque, MovimientoChequeCriteria> getMovimientoChequeDAO(){
		return new MovimientoChequeJPADAO();
	}
	
	/**
	 * dao para movimientos de notas de crédito
	 * @return
	 */
	public static IGenericDAO<MovimientoNotaCredito, MovimientoNotaCreditoCriteria> getMovimientoNotaCreditoDAO(){
		return new MovimientoNotaCreditoJPADAO();
	}	
	
	/**
	 * dao para movimientos de cuentas
	 * @return
	 */
	public static IGenericDAO<MovimientoCuenta, MovimientoCuentaCriteria> getMovimientoCuentaDAO(){
		return new MovimientoCuentaJPADAO();
	}
	
	/**
	 * dao para conceptos de caja
	 * @return
	 */
	public static IGenericDAO<ConceptoMovimiento, ConceptoMovimientoCriteria> getConceptoMovimientoDAO(){
		return new ConceptoMovimientoJPADAO();
	}

	/**
	 * dao para balances
	 * @return
	 */
	public static IBalanceDAO getBalanceDAO(){
		return new BalanceJPADAO();
	}

	/**
	 * dao para notas de crédito
	 * @return
	 */
	public static IGenericDAO<NotaCredito, NotaCreditoCriteria> getNotaCreditoDAO(){
		return new NotaCreditoJPADAO();
	}

	/**
	 * dao para cheques
	 * @return
	 */
	public static IGenericDAO<Cheque, ChequeCriteria> getChequeDAO(){
		return new ChequeJPADAO();
	}
	
	/**
	 * dao para gastos
	 * @return
	 */
	public static IGenericDAO<Gasto, GastoCriteria> getGastoDAO(){
		return new GastoJPADAO();
	}

}
