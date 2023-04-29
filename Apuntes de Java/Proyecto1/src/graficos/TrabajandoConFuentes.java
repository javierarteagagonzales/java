package graficos;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class TrabajandoConFuentes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoConFuentes mimarco=new MarcoConFuentes();
		mimarco.setVisible(true);
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MarcoConFuentes extends JFrame{
	public MarcoConFuentes() {
		
		setSize(400,400);
		setTitle("Prueba con Fuentes");
		LaminaConFuentes milamina=new LaminaConFuentes();
		add(milamina);
		milamina.setBackground(SystemColor.window);

	}
}

class LaminaConFuentes extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D) g;
		Font mifuente=new Font("Arial",Font.BOLD,26);
		g2.setColor(new Color(24,100,125));
		g2.setFont(mifuente);g2.drawString("Javier", 100, 100);
		

	}
}