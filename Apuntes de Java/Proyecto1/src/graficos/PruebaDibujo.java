package graficos;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class PruebaDibujo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoConDibujos mimarco=new MarcoConDibujos();
		mimarco.setVisible(true);
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

class MarcoConDibujos extends JFrame{
	public MarcoConDibujos() {
		
		setSize(450,450);
		//setLocation(400,200);
		setTitle("prueba de dibujo");
		LaminaConFiguras milamina=new LaminaConFiguras();
		add(milamina);
		milamina.setBackground(SystemColor.window);

	}
}

class LaminaConFiguras extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*
		g.drawRect(50, 50, 200, 200);
		g.drawLine(100, 100, 300, 200);
		g.drawArc(50, 100, 100, 200, 120, 150);
		*/
		Graphics2D g2=(Graphics2D) g;
		Rectangle2D rectangulo=new Rectangle2D.Double(100,100,200,150);
		g2.setPaint(Color.RED);
		g2.fill(rectangulo);
		//g2.draw(rectangulo);
		Ellipse2D elipse=new Ellipse2D.Double();
		elipse.setFrame(rectangulo);
		g2.setPaint(new Color(0,140,150).brighter()); //color RGB y brillo
		g2.fill(elipse);
		//g2.draw(elipse);
		
		/*
		 *linea
		g2.draw(new Line2D.Double(100,100,300,250));
		
		//circulo exterior
		double CentroenX=rectangulo.getCenterX();
		double CentroenY=rectangulo.getCenterY();
		double radio=150;
		Ellipse2D circulo=new Ellipse2D.Double();
		circulo.setFrameFromCenter(CentroenX,CentroenY,CentroenX+radio,CentroenY+radio);
		g2.draw(circulo);
		*/
	}
}