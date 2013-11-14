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

}
