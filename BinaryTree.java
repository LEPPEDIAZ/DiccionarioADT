import java.util.*;

/**
 * Basado en: 
 * The generic Binary Search Tree class (V.S. Adamchik 2010)
 * Tomado de: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
 * @param <E> genérico
 */

public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {
	private Node<E> raiz;
	private Comparator<E> comparador;
	private ArrayList<String> recorrido; //guarda el recorrido
	
	/**
	 * Constructor de un árbol binario con comparador nulo
	 */
	public BinaryTree(){
		raiz = null;
		comparador = null;
		recorrido = new ArrayList<String>();		
	}
	
	
	/**
	 * Constructor de un árbol binario con un comparador
	 * @param comparator
	 */
	BinaryTree(Comparator<E> comparator){
		raiz = null;
		comparador = comparator;
		recorrido = new ArrayList<String>();
	}
	
	
	/**
	 * Compara dos elementos
	 * @param e1
	 * @param e2
	 * @return int
	 */
	private int compare(E e1, E e2){
		if(comparador == null) 
			return e1.compareTo(e2);
		else
			return comparador.compare(e1, e2);
	}
	
	/**
	 * Busca un elemento en el árbol binario
	 * @param busqueda, que es el elemento a ser buscado
	 * @return
	 */
	public boolean buscar(E busqueda){
		return buscar(raiz, busqueda);
	}
	
	private boolean buscar(Node<E> algo, E busqueda){
		if(algo==null)
			return false;
		else if(compare(busqueda, algo.datos)==0)
			return true;
		else if(compare(busqueda, algo.datos)<0)
			return buscar(algo.izq, busqueda);
		else
			return buscar(algo.der, busqueda);
	}
	
	/**
	 * Inserta un dato o elemento al árbol binario
	 * @param datos, el elemento que se quiere insertar
	 */
	public void insert(E datos){
		raiz = insert(raiz, datos);
	}
	
	private Node<E> insert (Node<E> algo, E insertar){
		if (algo == null)
			return new Node<E>(insertar);
		if(compare(insertar, algo.datos)==0)
			return algo;
		if(compare(insertar, algo.datos)<0)
			algo.izq = insert(algo.izq, insertar);
		else
			algo.der = insert(algo.der, insertar);	
		return algo;
	}
	
	/**
	 * Devuelve un elemento
	 * @param algo
	 * @return
	 */
	private E recuperarDatos(Node<E> algo){
		while(algo.der !=null)
			algo = algo.der;
		return algo.datos;
	}
	
	/**
	 * Elimina 	un elemento del árbol binario
	 * @param borrar, el elemento a borrar
	 */
	public void delete(E borrar){
		raiz = delete(raiz, borrar);
	} 
	
	private Node<E> delete(Node<E> algo, E borrar){
		if(algo==null)
			throw new RuntimeException("No puede ser borrado");
		else if(compare(borrar, algo.datos)<0)
			algo.izq = delete(algo.izq, borrar);
		else if(compare(borrar, algo.datos)>0)
			algo.der = delete(algo.der, borrar);
		else{
			if(algo.izq==null)
				return algo.der;
			else if(algo.der==null)
				return algo.izq;
			else{
				algo.datos = recuperarDatos(algo.izq);
				algo.izq = delete(algo.izq, algo.datos);
			}
		}
		return algo;
	}
	
	/**
	 * Cadena de texto con todos los datos del árbol binario
	 */
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for(E datos:this)
			buffer.append(datos.toString()+ " ");
		return buffer.toString();
	}
	
	/**
	 * Recorre el árbol en in-order
	 * @param r
	 */
	private void inOrderHelper(Node r){
		if(r != null){
			inOrderHelper(r.izq);
			recorrido.add(r.toString());
			inOrderHelper(r.der);
		}
	}
	public void inOrderTraversal(){
		inOrderHelper(raiz);
	}
	
	/**
	 * 
	 * @return un ArrayList con el recorrido in-order
	 */
	public ArrayList<String> getTraversal(){
		return this.recorrido;
	}
	
	/**
	 * Crea el iterador
	 */
	public Iterator<E> iterator(){
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<E>{
		Stack<Node<E>> stack = new Stack<Node<E>>();
		
		public MyIterator(){
			if(raiz !=null)stack.push(raiz);
		}
		
		public boolean hasNext(){
			return !stack.isEmpty();
		}
		
		public E next(){
			Node<E> cur = stack.peek();
			if(cur.izq != null)
				stack.push(cur.izq);
			else{
				Node<E> temp = stack.pop();
				while(temp.der==null){
					if(stack.isEmpty())
						return cur.datos;
					temp = stack.pop();
				}
				stack.push(temp.der);
			}
			return cur.datos;			
		}
	}
	
	public void remove(){
		
	}
	
	private class Node<E>{
		private E datos;
		private Node<E> izq;
		private Node<E> der;
		
		public Node(E datos, Node<E> l, Node<E> r){
			izq = l;
			der = r;
			this.datos = datos;
		}
		
		public Node(E datos){
			this(datos, null, null);
		}
		
		public String toString(){
			return datos.toString();
		}	
	}
	
	class comp implements Comparator<Integer>{
		public int compare(Integer x, Integer y){
			return y-x;
		}
	}
}

