package chat;

import java.awt.BorderLayout;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoServidor miMarco = new MarcoServidor();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor() {
		setBounds(900, 300, 280, 350);
		JPanel miLamina = new JPanel();
		miLamina.setLayout(new BorderLayout());
		areaTexto = new JTextArea();
		miLamina.add(areaTexto,BorderLayout.CENTER);
		add(miLamina);
		setVisible(true);
		Thread miHilo = new Thread(this);
		miHilo.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// Crea un socket de server indicando el puerto por el que debe escuchar
			@SuppressWarnings("resource")
			ServerSocket servidor = new ServerSocket(9999);
			String nick, ip, mensaje;
			PaqueteEnvio paqueteRecibido;
			ArrayList<String> listaIp = new ArrayList<String>();
			while(true) {
				// Escucha la conexion que se hara y la acepta
				Socket miSocket = servidor.accept();
				
				ObjectInputStream paqueteDatos = new ObjectInputStream(miSocket.getInputStream());
				paqueteRecibido = (PaqueteEnvio) paqueteDatos.readObject();
				nick = paqueteRecibido.getNick();
				ip = paqueteRecibido.getIp();
				mensaje = paqueteRecibido.getMensaje();
				
				if(!mensaje.equals("online")) {
					areaTexto.append(nick + ": " + mensaje + " para " + ip + "\n");
					Socket enviaDestinatario = new Socket(ip, 9090);
					ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
					paqueteReenvio.writeObject(paqueteRecibido);
					paqueteReenvio.close();
					enviaDestinatario.close();
				} else {
					// Detecta ONLINE
					InetAddress localizacion = miSocket.getInetAddress();
					String ipRemota = localizacion.getHostAddress();
					System.out.println("online: " + ipRemota);
					listaIp.add(ipRemota);
					paqueteRecibido.setIps(listaIp);
					for(String z: listaIp){
						Socket enviaDestinatario = new Socket(z, 9090);
						ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
						paqueteReenvio.writeObject(paqueteRecibido);
						paqueteReenvio.close();
						enviaDestinatario.close();
					}
				}
				miSocket.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private	JTextArea areaTexto;

}