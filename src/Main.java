import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Programa principal
 * @author Alejandro Vasquez, Emilio Diaz *
 */

public class Main {
	public static void main(String[] args) throws IOException{
		
		ArrayList<String> diccionario = new ArrayList<String>();
		Association<String, String> asociacion = new Association<String, String>();
		BinaryTree<String> arbol = new BinaryTree<String>();
		
		BufferedReader tempDicc = new BufferedReader(new FileReader(new File("diccionario.txt"))); //Lee el diccionario
		String line;
		while ((line = tempDicc.readLine()) != null){ //Guarda las llaves del diccionario en un String
			diccionario.add(line); 
		}
		
		for(int i = 0; i<diccionario.size(); i++){ //Inserta las llaves del diccionario en una asociación y su llave en el árbol binario
			String temporal = diccionario.get(i).substring(1, diccionario.get(i).length()-1);
			String [] temp = temporal.split(", ");
			asociacion.put(temp[0], temp[1]);
			arbol.insert(temp[0]);
		}
		
		String textoTrad = "";
		BufferedReader tempTxt = new BufferedReader(new FileReader(new File("texto.txt"))); //Lee el archivo a traducir
		textoTrad = tempTxt.readLine(); //Coloca el texto en una variable String
		
		String[] dividirTxt = textoTrad.split(" "); //Separa el texto
		String traduccion = "";
		
		for(int i = 0; i<dividirTxt.length;i++){
			String minuscula = dividirTxt[i].toLowerCase();//Coloca cada palabra a traducir en minúscula
			if(asociacion.get(minuscula)==null){
				traduccion += "*" + dividirTxt[i] + "* "; //Si no está la palabra en inglés la "envuelve" en asteriscos
			}
			else{
				traduccion += asociacion.get(minuscula) + " "; //Si se encuentra la palabra en el diccionario, la traduce
			}
		}
		
		System.out.println(traduccion);
		
	}
}
