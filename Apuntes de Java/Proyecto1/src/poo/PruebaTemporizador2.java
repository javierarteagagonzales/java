package poo;

import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class PruebaTemporizador2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Reloj mireloj=new Reloj(3000,true);
		Reloj mireloj=new Reloj();
		mireloj.enMarcha(3000,true);
		JOptionPane.showMessageDialog(null, "Pulsa aceptar para acabar");
		System.exit(0);;
	}

}

class Reloj{
	/*public Reloj(int intervalo, final boolean sonido) {
		this.intervalo=intervalo;
		this.sonido=sonido;
	}*/
	public void enMarcha(int intervalo, boolean sonido) {
		class DameLaHora2 implements ActionListener{//clase interna local
		
		public void actionPerformed(ActionEvent e) {
			Date ahora=new Date();
			System.out.println("Te pongo la hora cada 3 segundos "+ ahora);
			if(sonido) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		
	}
		
		ActionListener oyente=new DameLaHora2();
		Timer mitemporizador=new Timer(intervalo, oyente);
		mitemporizador.start();
	}
	
	private int intervalo;
	private boolean sonido;
	
	/*private class DameLaHora2 implements ActionListener{//clase interna
		
		public void actionPerformed(ActionEvent e) {
			Date ahora=new Date();
			System.out.println("Te pongo la hora cada 3 segundos "+ ahora);
			if(sonido) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		
	}*/
}
