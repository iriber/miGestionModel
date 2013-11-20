package com.migestion.i18n;

import java.io.IOException;
import java.util.Properties;


public class Messages {

	public static Properties properties = getProperties();
	
    public static Properties getProperties(){
    	Properties props = new Properties();
    	try {
			props.load(Messages.class.getResourceAsStream("/META-INF/messages.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return props;
    };
    
    
	public static String locale( String key){
		
		return properties.getProperty(key);
	}

	
	public static String PRODUCTO_NOMBRE_REQUERIDO =locale("producto.nombre.requerido");
	public static String PRODUCTO_PRECIO_REQUERIDO =locale("producto.precio.requerido");
	public static String PRODUCTO_IVA_REQUERIDO =locale("producto.iva.requerido");
	public static String PRODUCTO_CATEGORIA_REQUERIDA =locale("producto.categoria.requerida");
	public static String PRODUCTO_NOMBRE_REPETIDO =locale("producto.nombre.repetido");
	public static String PRODUCTO_TIENE_DEPENDENCIAS =locale("producto.tiene.dependencias");
	
	public static String CATEGORIA_PRODUCTO_NOMBRE_REQUERIDO =locale("categoriaProducto.nombre.requerido");
	public static String CATEGORIA_PRODUCTO_NOMBRE_REPETIDO =locale("categoriaProducto.nombre.repetido");
	public static String CATEGORIA_PRODUCTO_TIENE_DEPENDENCIAS =locale("categoriaProducto.tiene.dependencias");
	
	public static String CLIENTE_NOMBRE_REQUERIDO =locale("cliente.nombre.requerido");
	public static String CLIENTE_NOMBRE_REPETIDO =locale("cliente.nombre.repetido");
	public static String CLIENTE_DUPLICADO =locale("cliente.duplicado");
	public static String CLIENTE_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO =locale("cliente.nombre.repetido.nroDocumento.requerido");
	public static String CLIENTE_TIENE_DEPENDENCIAS =locale("cliente.tiene.dependencias");

	public static String VENDEDOR_NOMBRE_REQUERIDO =locale("vendedor.nombre.requerido");
	public static String VENDEDOR_NOMBRE_REPETIDO =locale("vendedor.nombre.repetido");
	public static String VENDEDOR_DUPLICADO =locale("vendedor.duplicado");
	public static String VENDEDOR_NOMBRE_REPETIDO_NRO_DOCUMENTO_REQUERIDO =locale("vendedor.nombre.repetido.nroDocumento.requerido");
	public static String VENDEDOR_TIENE_DEPENDENCIAS =locale("vendedor.tiene.dependencias");
	
	public static String PROVEEDOR_NOMBRE_REQUERIDO =locale("proveedor.nombre.requerido");
	public static String PROVEEDOR_NOMBRE_REPETIDO =locale("proveedor.nombre.repetido");
	public static String PROVEEDOR_DUPLICADO =locale("proveedor.duplicado");
	public static String PROVEEDOR_NOMBRE_REPETIDO_CUIT_REQUERIDO =locale("proveedor.nombre.repetido.cuit.requerido");
	public static String PROVEEDOR_TIENE_DEPENDENCIAS =locale("proveedor.tiene.dependencias");

	public static String CUENTA_BANCARIA_NOMBRE_REQUERIDO =locale("cuentaBancaria.nombre.requerido");
	public static String CUENTA_BANCARIA_TITULAR_REQUERIDO =locale("cuentaBancaria.titular.requerido");
	public static String CUENTA_BANCARIA_NRO_CUENTA_REQUERIDO =locale("cuentaBancaria.nroCuenta.requerido");
	public static String CUENTA_BANCARIA_NOMBRE_REPETIDO =locale("cuentaBancaria.nombre.repetido");
	public static String CUENTA_BANCARIA_NOMBRE_REPETIDO_NRO_CUENTA_REQUERIDO =locale("cuentaBancaria.nombre.repetido.nroCuenta.requerido");
	public static String CUENTA_BANCARIA_TIENE_DEPENDENCIAS =locale("cuentaBancaria.tiene.dependencias");
	public static String CUENTA_BANCARIA_DUPLICADA =locale("cuentaBancaria.duplicada");
	
	public static String VENTA_FECHA_REQUERIDA =locale("venta.fecha.requerida");
	public static String VENTA_SUCURSAL_REQUERIDA =locale("venta.sucursal.requerida");
	public static String VENTA_CLIENTE_REQUERIDO =locale("venta.cliente.requerido");
	public static String VENTA_VENDEDOR_REQUERIDO =locale("venta.vendedor.requerido");
	public static String VENTA_PRODUCTOS_REQUERIDOS =locale("venta.productos.requeridos");
	public static String VENTA_PRODUCTOS_INACTIVOS =locale("venta.productos.inactivos");
	public static String VENTA_MONTO_INCORRECTO =locale("venta.monto.incorrecto");

	public static String PAGO_FECHA_REQUERIDA =locale("pago.fecha.requerida");
	public static String PAGO_SUCURSAL_REQUERIDA =locale("pago.sucursal.requerida");
	public static String PAGO_CLIENTE_REQUERIDO =locale("pago.cliente.requerido");
	public static String PAGO_VENTAS_REQUERIDAS =locale("pago.ventas.requeridas");
	public static String PAGO_VENTAS_INCORRECTAS =locale("pago.ventas.incorrectas");
	public static String PAGO_MONTO_INCORRECTO =locale("pago.monto.incorrecto");
	public static String PAGO_FORMAS_PAGO_REQUERIDAS =locale("pago.formasPago.requeridas");
	
}
