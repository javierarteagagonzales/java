package graficos;

import java.awt.GraphicsEnvironment;
import javax.swing.*;

public class Pruebass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Programa para pregntar si está isntalada un fuente en el ordenador
		String fuente=JOptionPane.showInputDialog("Introduce Fuente");
		boolean estalafuente=false;
		
		//Qué fuente hay en tu sistema
		String [] nombresDeFuentes=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		for(String nombredelafuente: nombresDeFuentes) {
			//System.out.println(nombredelafuente);
			if(nombredelafuente.equals(fuente)) {
				estalafuente=true;
			}
		}
		if(estalafuente) {
			System.out.println("Fuente instalada");
		}else {
			System.out.println("No está instalada la fuente");
		}
	}

}
