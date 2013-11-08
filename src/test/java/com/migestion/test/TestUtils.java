package com.migestion.test;

import java.util.List;
import java.util.Random;

import com.migestion.model.GenericEntity;

/**
 * Utilidades para los test.
 *
 * @author bernardo
 * @since 16/05/2012
 *
 */
public class TestUtils {

	public static char[] alpha = getAlpha();
	public static char[] alphanumeric = getAlphanumeric();
	public static char[] numeric = getNumeric();
	public static Random rnd = new Random();

	/**
	 * nos retorna un object de la lista.
	 * @param entities
	 * @return GenericEntity
	 */
	public static Object getRandom(List entities){
		
		if( !entities.isEmpty()){
			int min = 0;
			int max = entities.size()-1;
			int index = rnd.nextInt(max - min + 1) + min;
			return entities.get(index);	
		}else
			return null;
	}
	
	public static Object getRandom(Object[] entities){
		
		if( entities.length > 0){
			int min = 0;
			int max = entities.length-1;
			int index = rnd.nextInt(max - min + 1) + min;
			return entities[index];	
		}else
			return null;
	}
	/**
	 * nos retorna un número de dni al azar.
	 * @return
	 */
	public static Integer getRandomDNI(){
		
		int min = 5000000;
		int max = 40000000;
		return rnd.nextInt(max - min + 1) + min;
	}
	
	
	/**
	 * nos retorna un nombre al azar.
	 * @return
	 */
	public static String getRandomNombre(){
		
		String[] nombres = {"Alberto", "Juan", "Carlos", "Marta", "Lorena",
				"Simón", "Antonio", "Florencia", "María Marta", "Agustina",
				"Ricardo", "Gonzalo", "Jaime", "Jorge", "Bernardo",
				"Agustín", "Mario", "Horacio", "Hernán", "David",
				"Lucas", "Luciano", "Luciana", "Lourdes", "Lola",
				"Marcelo", "Marcela", "Mauro", "Miguel", "Mariano",
				"Rita", "Renata", "Rubén", "Gerónimo", "Fernando",
				"Juana", "Roberto", "Carola", "Ramiro", "Mariana"};
		
		return getRandomTexto( nombres );
	}
	
	/**
	 * nos retorna un apellido al azar.
	 * @return
	 */
	public static String getRandomApellido(){
		
		String[] apellidos = {"Perez", "Torres", "Sanchez", "Pineda", "Warner",
							"Altamira", "Almirante", "Almidón", "Arruabarrena", "Arte",
							"Barrionuevo", "Bruno", "Brisa", "Barrera", "Bermúdez",
							"Camino", "Capusoto", "Chavez", "Cabrera", "Cobos",
							"Delorte", "Dalessandro", "Denisio", "Di Lorenzo", "D'Andrea"};
		
		return getRandomTexto(apellidos);
	}	



	/**
	 * nos retorna un email al azar.
	 * @return
	 */
	public static String getRandomEmail(){
		
		String[] usuarios = {"toto", "montolo", "mimail", "mio2011", "otromail"};
		
		String[] dominios = {"gmail.com", "hotmail.com", "yahoo.com", "yahoo.com.ar"};
		
		return getRandomTexto( usuarios) + "@" + getRandomTexto( dominios);
	}	
	
	/**
	 * dado una lista de textos, retorna un elemento al azar.
	 * @param textos lista de textos
	 * @return
	 */
	private static String getRandomTexto( String[] textos ){
		
		int min = 0;
		int max = textos.length -1;
		int index = rnd.nextInt(max - min + 1) + min;
		
		return textos[ index ];
	}
	


	/**
	 * @return
	 */
	public static Integer getRandomInt(int min, int max){
		
		return rnd.nextInt(max - min + 1) + min;
	}

	/**
	 * @return
	 */
	public static Float getRandomFLoat(int min, int max){
		
		Integer num = rnd.nextInt(max - min + 1) + min;
		
		return num.floatValue();
		
	}
	
	public static String getTexto(int len) {
        StringBuffer out = new StringBuffer();
        
        while (out.length() < len) {
            int idx = Math.abs((rnd.nextInt() % alpha.length));
            out.append(alpha[idx]);
        }
        return out.toString();
    }

	
	public static String getTextoNumero(int len) {
        StringBuffer out = new StringBuffer();
        
        while (out.length() < len) {
            int idx = Math.abs((rnd.nextInt() % numeric.length));
            out.append(numeric[idx]);
        }
        return out.toString();
    }

	// create alphanumeric char array
    private static char[] getAlphanumeric() {
        StringBuffer buf = new StringBuffer(128);
        for (int i = 48; i <= 57; i++) {
            buf.append((char) i); // 0-9
        }
        for (int i = 65; i <= 90; i++) {
            buf.append((char) i); // A-Z
        }
        for (int i = 97; i <= 122; i++) {
            buf.append((char) i); // a-z
        }
        return buf.toString().toCharArray();
    }


	// create alphanumeric char array
    private static char[] getAlpha() {
        StringBuffer buf = new StringBuffer(128);
        for (int i = 65; i <= 90; i++) {
            buf.append((char) i); // A-Z
        }
        for (int i = 97; i <= 122; i++) {
            buf.append((char) i); // a-z
        }
        return buf.toString().toCharArray();
    }
	// create numeric char array
    private static char[] getNumeric() {
        StringBuffer buf = new StringBuffer(128);
        for (int i = 48; i <= 57; i++) {
            buf.append((char) i); // 0-9
        }
        return buf.toString().toCharArray();
    }
}
