import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;



public class Traductor<E> {



	private String cadena = "";

	private Arbol<E> arbol= null; //lo tenia en mi correo con mi otra pareja pero se lo agregue al de alejandro

	

	public Traductor() throws IOException {

		// TODO Auto-generated constructor stub

		String palabras="";

		BufferedReader br = new BufferedReader(new FileReader("freedic-eng-spa.txt"));

		try {

		    StringBuilder sb = new StringBuilder();

		    String line = br.readLine();									

		    while (line != null) {												

		        sb.append(line);

		        sb.append(System.lineSeparator());

		        if(arbol==null){

		        	arbol = new Arbol(new Association(line.substring(1, line.indexOf(',')),  line.substring(line.indexOf(',')+1,line.length()-1)) );//parcear linea y crear raiz y Association(ingles,espanol)

		        }else{

		        	arbol.Agregar(new Association(line.substring(1, line.indexOf(',')),  line.substring(line.indexOf(',')+1,line.length()-1)));		////parcear linea y agregar nodo y Association(ingles,espanol)

		        }	

		        line = br.readLine();											
			 palabras = sb.toString();
		       

		    }

	}



	

	public String traducir(String texto){	
		// lo copie de internet.

		String[] words = texto.split("\\t+");								

			for (int i = 0; i < words.length; i++) {

				words[i] = words[i].replaceAll("[^\\w]", "");					
			 	
				cadena = cadena  +arbol.Traducir(words[i])+" ";					
			   

			}

			return  cadena;

	}

}
