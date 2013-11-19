package com.migestion.utils;

import java.util.ArrayList;

import com.migestion.model.Producto;

public class XlsProductoReader extends XlsReader{

		
	protected Object buildObject(ArrayList<Object> columns){
		
		Producto p = new Producto();
		
		p.setNombre( getColumnString(columns, 1)  );
		
		p.setDescripcion( getColumnString(columns, 2) );
		
		p.setPrecio( getColumnFloat(columns, 3) );
		
		p.setIva( getColumnFloat(columns, 4) );
		
		p.setStockActual( getColumnInt(columns, 5) );
		
		p.setStockMinimo( getColumnInt(columns, 6) );
		
		p.setStockMaximo( getColumnInt(columns, 7) );
		
		return p;
		
	}
	
	private Object getColumn(ArrayList<Object> columns, int index ){
		
		if(columns.size()>index && columns.get(index)!=null)
			return columns.get(index);
		else
			return null;
	}
	
	private String getColumnString(ArrayList<Object> columns, int index ){
		
		Object value = getColumn(columns, index);
		if( value!=null )
			return value.toString();
		else
			return null;
	}

	private Integer getColumnInt(ArrayList<Object> columns, int index ){
		
		Object value = getColumn(columns, index);
		if( value!=null )
			return ((Double)value).intValue();
		else
			return null;
	}
	
	private Float getColumnFloat(ArrayList<Object> columns, int index ){
		
		Object value = getColumn(columns, index);
		if( value!=null )
			return ((Double)value).floatValue();
		else
			return null;
	}
}
