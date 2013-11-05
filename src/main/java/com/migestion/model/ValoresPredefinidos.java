package com.migestion.model;

import java.io.IOException;
import java.util.Properties;


public class ValoresPredefinidos {

	public static Properties prop = getProperties();
	
	public static final Long CONCEPTO_MOVIMIENTO_VENTAS = Long.valueOf( prop.getProperty("concepto.movimiento.ventas") );
	
	public static final Long CLIENTE_MOSTRADOR = Long.valueOf( prop.getProperty("cliente.mostrador") );
	
	public static final Long VENDEDOR_TITULAR_COMERCIO = Long.valueOf( prop.getProperty("vendedor.titular.comercio") );
	
	public static final Long SUCURSAL_CASA_MATRIZ = Long.valueOf( prop.getProperty("sucursal.casa.matriz") );;
	
	public static final Long CATEGORIA_PRODUCTO_GENERAL = Long.valueOf( prop.getProperty("categoriaProducto.general") );;
	
	//private static ResourceBundle prop = ResourceBundle.getBundle("i18n");  
    
    public static Properties getProperties(){
    	Properties props = new Properties();
    	try {
			props.load(ValoresPredefinidos.class.getResourceAsStream("/META-INF/valores_predefinidos.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return props;
    };
    
    


}
