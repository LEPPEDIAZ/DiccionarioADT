import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class MainTraductor {

	

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in); 
		String cadena;
		String texto = "";
		String textoTraducido = "";
		BinarySearchTree<Asociacion<String, String>> b = new BinarySearchTree<>();
		FileReader fr = new FileReader("diccionario.txt");
	 	BufferedReader bf = new BufferedReader(fr);
		while ((cadena = bf.readLine()) != null) {

			cadena = cadena.replaceAll("[()]", "");
			String[] datos = cadena.split("\\s*,\\s*");
			b.insert(new Asociacion<String, String>(datos[0], datos[1]));
		}

		bf.close();
		text = new FileReader("texto.txt");
		buffer = new BufferedReader(fr);

		while ((cadena = buffer.readLine()) != null) {
			texto += cadena + "\n";
			String[] palabras = cadena.split("\\s+");

			for(int i=0; i<palabras.length;i++){
				Asociacion<String, String> match = b.find(new Asociacion<String, String>(palabras[i], ""));
				if(match != null){
					textoTraducido += match.value + " ";
				}else{
					textoTraducido += "*" + palabras[i] + "* ";

				}

			}

		}

		buffer.close();
		System.out.println(texto);
		System.out.println(textoTraducido);
	}

	

}