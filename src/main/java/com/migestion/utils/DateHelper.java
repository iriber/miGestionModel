package com.migestion.utils;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static Date[] getFechaHoraMinMax(Date fecha){
		
		Calendar c = Calendar.getInstance();
		c.setTime( fecha );
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);

		Date fecha1 = c.getTime();
		
		c.set(Calendar.HOUR, 11);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.AM_PM, Calendar.PM);
		
		Date fecha2 = c.getTime();
		
		Date[] fechas = {fecha1, fecha2}; 
		return fechas;
	}
}
