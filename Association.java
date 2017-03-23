import java.util.HashMap;

public class Association <K,V>{
	private HashMap<K,V> asociacion;
	
	public Association(){
		asociacion = new HashMap<K,V>();
	}
	
	public void put(K llave, V valor){
		asociacion.put(llave, valor);
	}
	
	public V get(Object llave){
		return asociacion.get(llave);
	}
	
	public boolean isEmpty(){
		return asociacion.isEmpty();
	}
	
}
