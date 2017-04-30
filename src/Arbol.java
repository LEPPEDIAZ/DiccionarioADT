public class Arbol<E>{



	protected Association palabra;

	protected Arbol<E> izquierda;					//o igual

	protected Arbol<E> derecha;  

	

	

	

	public Arbol(Association palabra) {				//crea el nodo

		this.palabra = palabra;

		izquierda= null;

		derecha=null;

	}

	

	public void Agregar(Association palabra){		// compara la palabra en ingles

		if(!this.palabra.equals(palabra)){

			if(this.palabra.compare(palabra)){		//si es mayor crea u nodo en la derecha

				izquierda= new Arbol(palabra);

			}else{									//si es menor o igual crea u nodo en la izquierda

				derecha=new Arbol(palabra);

				

			}

		}

	}

	

	public String Traducir(String ingles){			//realiza la traduccion de la palabra

		String espanol="";

		

		if((ingles.compareTo((String) palabra.getKey()) <0) & (izquierda!=null)){					//si la palbra es menor o igual a la del nodo y el nodo existe

			espanol= izquierda.Traducir(ingles);

		}else if(ingles.equals((String)palabra.getKey())){			//si la palabra es la de este nodo

			return (String) palabra.getValue();

		}else if(derecha!=null){					//si la palbra es mayor o igual a la del nodo y el nodo existe

			espanol= derecha.Traducir(ingles);

		}else{

			espanol="*"+ingles+"*";					//si la palabra no se encuentra en el diccionario

		}

		

		

		return espanol;

	}



}
