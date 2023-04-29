package poo;

import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class PruebaTemporizador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DameLaHora oyente=new DameLaHora();
		
		Timer mitemporizador =  new Timer(5000, oyente);
		
		mitemporizador.start();
		JOptionPane.showMessageDialog(null, "Pulsa Aceptar para detener");
		System.exit(0);
	}

}

class DameLaHora implements ActionListener{
	public void actionPerformed(ActionEvent e){
		Date ahora=new Date();
		System.out.println("Te pongo la hora cada 5 sg: "+ahora);
		Toolkit.getDefaultToolkit().beep();
	}

}
