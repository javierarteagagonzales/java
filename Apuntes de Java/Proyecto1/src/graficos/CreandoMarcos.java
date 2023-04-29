package graficos;

import java.awt.Frame;

import javax.swing.*;

public class CreandoMarcos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		miMarco marco1=new miMarco();
		marco1.setVisible(true);
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class miMarco extends JFrame{
	
	public miMarco() {
		//setSize(500,300);
		//setLocation(450,250);
		setBounds(500,300,425,225);
		setResizable(false); //no permitir redimensionar
		//setExtendedState(Frame.MAXIMIZED_BOTH);//abrir a pantalla completa
		setTitle("Principal");
		
	}
	
}
