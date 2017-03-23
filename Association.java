import java.util.HashMap;

/**
 * Realiza la asociación de palabras del diccionario, con la palabra en inglés como llave
 * y la palabra en español como valor.
 * @author Alejandro Vásquez, Emilio Díaz
 * @param <K> Llave genérica
 * @param <V> Valor genérico
 */

public class Association <K,V>{
	private HashMap<K,V> asociacion;
	
	public Association(){
		asociacion = new HashMap<K,V>();
	}
	
	/**
	 * Pone una palabra en inglés y su traducción a español en una asociación
	 * @param llave, palabra en inglés
	 * @param valor, palabra en español
	 */
	
	public void put(K llave, V valor){
		asociacion.put(llave, valor);
	}
	
	/**
	 * Devuelve la traducción de una palabra de inglés a español
	 * @param llave, que es la palabra en inglés
	 * @return la palabra en español
	 */
	
	public V get(Object llave){
		return asociacion.get(llave);
	}
	
	
	/**Ve si la lista de asociaciones se encuentra vacía
	 * @return true si la lista se encuentra vacía
	 * */
	
	public boolean isEmpty(){
		return asociacion.isEmpty();
	}
	
}
