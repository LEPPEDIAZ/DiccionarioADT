import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException{
		
		ArrayList<String> diccionario = new ArrayList<String>();
		Association<String, String> asociacion = new Association<String, String>();
		BinaryTree<String> arbol = new BinaryTree<String>();
				
		BufferedReader tempDicc = new BufferedReader(new FileReader(new File("diccionario.txt")));
		String line;
		while ((line = tempDicc.readLine()) != null){
			diccionario.add(line);
		}
		
		for(int i = 0; i<diccionario.size(); i++){
			String temporal = diccionario.get(i).substring(1, diccionario.get(i).length()-1);
			String [] temp = temporal.split(", ");
			asociacion.put(temp[0], temp[1]);
			arbol.insert(temp[0]);
		}
		
		String textoTrad = "";
		BufferedReader tempTxt = new BufferedReader(new FileReader(new File("texto.txt")));
		textoTrad = tempTxt.readLine();
		
		String[] dividirTxt = textoTrad.split(" ");
		String traduccion = "";
		
		for(int i = 0; i<dividirTxt.length;i++){
			String minuscula = dividirTxt[i].toLowerCase();
			if(asociacion.get(minuscula)==null){
				traduccion += "*" + dividirTxt[i] + "* ";
			}
			else{
				traduccion += asociacion.get(minuscula) + " ";
			}
		}
		
		System.out.println(traduccion);
		
	}
}
