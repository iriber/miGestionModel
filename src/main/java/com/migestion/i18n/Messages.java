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

}
