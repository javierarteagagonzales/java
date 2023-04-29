package graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PruebaAcciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoAccion miMarco = new MarcoAccion();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);
	}

}

class MarcoAccion extends JFrame {
	
	public MarcoAccion() {
		setTitle("Prueba Acciones");
		setBounds(600, 350, 600, 300);
		PanelAccion lamina = new PanelAccion();
		add(lamina);
	}
	
}

// clase lamina
class PanelAccion extends JPanel {

	public PanelAccion() {
		AccionColor accionAmarillo = new AccionColor("Amarillo", 
				new ImageIcon("src/graficos/bolaAmar.jpg"), Color.YELLOW);
		AccionColor accionAzul = new AccionColor("Azul", 
				new ImageIcon("src/graficos/bolaAzul.jpg"), Color.BLUE);
		AccionColor accionRojo = new AccionColor("Rojo", 
				new ImageIcon("src/graficos/bolaRoja.jpg"), Color.RED);
		
		add(new JButton(accionAmarillo));
		add(new JButton(accionAzul));
		add(new JButton(accionRojo));
		
//		JButton botonAmarillo = new JButton("Amarillo");
//		JButton botonAzul = new JButton("Azul");
//		JButton botonRojo = new JButton("Rojo");
//		add(botonAmarillo);
//		add(botonAzul);
//		add(botonRojo);
		
		// 1) Creamos mapa de entrada
//		InputMap mapaEntrada = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);	// Tambien funciona
		InputMap mapaEntrada = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		// 2) Creamos combinacion de teclas
		KeyStroke teclaAmarillo = KeyStroke.getKeyStroke("A");//tecla A
		KeyStroke teclaAzul = KeyStroke.getKeyStroke("ctrl B");
		KeyStroke teclaRojo = KeyStroke.getKeyStroke("ctrl R");
		// 3) Asignar combinacion de teclas a un objeto
		mapaEntrada.put(teclaAmarillo, "fondoAmarillo");
		mapaEntrada.put(teclaAzul, "fondoAzul");
		mapaEntrada.put(teclaRojo, "fondoRojo");
		// 4) Asignar objeto a accion
		ActionMap mapaAccion = getActionMap();
		mapaAccion.put("fondoAmarillo", accionAmarillo);
		mapaAccion.put("fondoAzul", accionAzul);
		mapaAccion.put("fondoRojo", accionRojo);
	}

	// clase oyente, cambia el color de fondo
	private class AccionColor extends AbstractAction {//clase interna para reconocer setBackground

		public AccionColor(String nombre, Icon icono, Color colorBtn) {
			putValue(Action.NAME, nombre);
			putValue(Action.SMALL_ICON, icono);
			putValue(Action.SHORT_DESCRIPTION, "Poner la lamina de color " + nombre);
			putValue("colorDeFondo", colorBtn);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Color c = (Color) getValue("colorDeFondo");
			setBackground(c);
			System.out.println("Nombre: " + getValue(Action.NAME) + " Descripcion: " 
					+ getValue(Action.SHORT_DESCRIPTION));
		}
		
	}
	
}