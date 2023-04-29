package graficos;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class PruebaImagenes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoImagen mimarco=new MarcoImagen();
		mimarco.setVisible(true);
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
class MarcoImagen extends JFrame{
	public MarcoImagen() {
		
		setBounds(150,150,300,200);
		setTitle("Marco con Imagen");
		LaminaConImagen milamina=new LaminaConImagen();
		add(milamina);
		milamina.setBackground(SystemColor.window);

	}
}

class LaminaConImagen extends JPanel{
	public LaminaConImagen() {
		try {
			imagen=ImageIO.read(new File("src/graficos/icono.png"));
			}
			catch(IOException e) {
				System.out.println("La imagen no se encuentra");
			}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//File miimagen = new File("src/graficos/icono.png");
		
		//dimensiones de imagen y dibuja
		int anchuraImagen=imagen.getWidth(this);
		int alturaImagen=imagen.getHeight(this);
		g.drawImage(imagen,0 , 0, null);
		//copia imagen
		g.copyArea(0, 0, anchuraImagen,alturaImagen, 150, 75);

	}
	private Image imagen;
}
