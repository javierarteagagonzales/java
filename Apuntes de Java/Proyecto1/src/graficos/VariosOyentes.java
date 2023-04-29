package graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VariosOyentes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoPrincipal miMarco = new MarcoPrincipal();
		miMarco.setVisible(true);
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MarcoPrincipal extends JFrame {

	public MarcoPrincipal() {
		setTitle("Prueba varios");
		setBounds(1000, 100, 300, 200);
		LaminaPrincipal lamina = new LaminaPrincipal();
		add(lamina);
	}
	
}

class LaminaPrincipal extends JPanel {
	
	public LaminaPrincipal() {
		JButton btnNuevo = new JButton("Nuevo");
		add(btnNuevo);
		btnCerrar = new JButton("Cerrar todo");
		add(btnCerrar);
		OyenteNuevo miOyente = new OyenteNuevo();
		btnNuevo.addActionListener(miOyente);
	}
	
	private class OyenteNuevo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MarcoEmergente marco = new MarcoEmergente(btnCerrar);
			marco.setVisible(true);
		}
		
	}
	
	JButton btnCerrar;
	
}

class MarcoEmergente extends JFrame {
	
	public MarcoEmergente(JButton btnFuente) {
		contador++;
		setTitle("Ventana " + contador);
		setBounds(40*contador, 40*contador, 300, 150);
		CierraTodos oyenteCerrar = new CierraTodos();
		btnFuente.addActionListener(oyenteCerrar);
	}

	private class CierraTodos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}
		
	}
	
	private static int contador = 0;
	
}