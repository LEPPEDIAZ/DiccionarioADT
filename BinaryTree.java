/*Basado en: 
 * The generic Binary Search Tree class (V.S. Adamchik 2010)
 * Tomado de: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
 * */

import java.util.*;


public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {
	private Node<E> raiz;
	private Comparator<E> comparador;
	private ArrayList<String> recorrido;
	
	public BinaryTree(){
		raiz = null;
		comparador = null;
		recorrido = new ArrayList<String>();		
	}
	
	BinaryTree(Comparator<E> comparator){
		raiz = null;
		comparador = comparator;
		recorrido = new ArrayList<String>();
	}
	
	private int compare(E e1, E e2){
		if(comparador == null) 
			return e1.compareTo(e2);
		else
			return comparador.compare(e1, e2);
	}
	
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
	
	private E recuperarDatos(Node<E> algo){
		while(algo.der !=null)
			algo = algo.der;
		return algo.datos;
	}
	
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
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for(E datos:this)
			buffer.append(datos.toString());
		return buffer.toString();
	}
	
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
	
	public ArrayList<String> getTraversal(){
		return this.recorrido;
	}
	
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

