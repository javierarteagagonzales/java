package uso_threads;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UsoThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame marco = new MarcoRebote();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);
	}

}

class PelotaHilos implements Runnable {

	public PelotaHilos(Pelota unaPelota, Component unComponente) {
		pelota = unaPelota;
		componente = unComponente;
	}
	
	// Caso 1: Metodo con sleep que ejecuta de forma visible (no puedo usar el boton detener)
	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		for(int i = 1; i <= 3000; i++){
//			pelota.muevePelota(componente.getBounds());
//			componente.paint(componente.getGraphics());
//			try {
//				Thread.sleep(4);	// Long en ms
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Hilo bloqueado. Imposible su interrupcion.");
//				e.printStackTrace();
//			}
//		}
//	}
	
	// Caso 2: Metodo run ejecuta rapidamente sin sleep en un while pero puedo usar detener con
	// el metodo de clase interrupted de la clase Thread
	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(!Thread.interrupted()) {
//			pelota.muevePelota(componente.getBounds());
//			componente.paint(componente.getGraphics());
//		}
//	}
	
	// Caso 3: Metodo run ejecuta rapidamente sin sleep en un while usando el metodo
	// de instancia isInterrupted de un hilo en concreto (usando en este caso el hilo 
	// actual con el metodo de clase currentThread)
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Estado del hilo al comenzar: " + Thread.currentThread().isInterrupted());
		
		while(!Thread.currentThread().isInterrupted()) {
			pelota.muevePelota(componente.getBounds());
			componente.paint(componente.getGraphics());
		}
		
		System.out.println("Estado del hilo al terminar: " + Thread.currentThread().isInterrupted());
	}
	
	private Pelota pelota;
	private Component componente;
	
}

// Movimiento de la pelota
class Pelota {
	
	// Mueve la pelota invirtiendo posici�n si choca con l�mites
	public void muevePelota(Rectangle2D limites) {
		x += dx;
		y += dy;
		
		if(x < limites.getMinX()) {
			x = limites.getMinX();
			dx = -dx;
		}
		
		if(x + TAMX >= limites.getMaxX()) {
			x = limites.getMaxX() - TAMX;
			dx = -dx;
		}
		
		if(y < limites.getMinY()) {
			y = limites.getMinY();
			dy = -dy;
		}
		
		if(y + TAMY >= limites.getMaxY()) {
			y = limites.getMaxY() - TAMY;
			dy = -dy;
		}
		
	}
	
	// Forma de la pelota en su posici�n inicial	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, TAMX, TAMY);
	}	
	
	private static final int TAMX = 15;
	private static final int TAMY = 15;
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	
}

// L�mina que dibuja las pelotas
class LaminaPelota extends JPanel {
	
	//A�adimos pelota a la l�mina
	public void add(Pelota b) {
		pelotas.add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for(Pelota b: pelotas)
			g2.fill(b.getShape());
	}
	
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
	
}


// Marco con l�mina y botones
class MarcoRebote extends JFrame {
	
	public MarcoRebote(){
		setBounds(600, 300, 400, 350);
		setTitle("Rebotes");
		lamina = new LaminaPelota();
		add(lamina, BorderLayout.CENTER);
		JPanel laminaBotones=new JPanel();
		
		ponerBoton(laminaBotones, "Dale!", new ActionListener() {
			
			public void actionPerformed(ActionEvent evento) {
				// TODO Auto-generated method stub
				comienzaElJuego();
			}
			
		});
		
		ponerBoton(laminaBotones, "Detener", new ActionListener() {
			
			public void actionPerformed(ActionEvent evento) {
				// TODO Auto-generated method stub
				detener();
			}
			
		});
		
		ponerBoton(laminaBotones, "Salir", new ActionListener() {
			
			public void actionPerformed(ActionEvent evento) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		add(laminaBotones, BorderLayout.SOUTH);
	}
	
	// Ponemos botones
	public void ponerBoton(Container c, String titulo, ActionListener oyente) {
		JButton boton = new JButton(titulo);
		c.add(boton);
		boton.addActionListener(oyente);
	}
	
	// A�ade pelota y la bota 1000 veces	
	public void comienzaElJuego() {
		Pelota pelota = new Pelota();
		lamina.add(pelota);
		Runnable r = new PelotaHilos(pelota, lamina);
//		Thread t = new Thread(r);
		t = new Thread(r);
		t.start();
	}
	
	// Detenemos el hilo
	public void detener() {
//		t.stop();		// este metodo stop() se encuentra obsoleto
		t.interrupt();	// si lo uso cuando hay sleep() genera InterruptedException
	}
	
	private Thread t;
	private LaminaPelota lamina;
	
}