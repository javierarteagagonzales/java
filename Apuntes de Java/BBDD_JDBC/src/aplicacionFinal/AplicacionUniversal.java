package aplicacionFinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AplicacionUniversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoBBDD mimarco = new MarcoBBDD();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);

	}

}

class MarcoBBDD extends JFrame {

	public MarcoBBDD() {
		setBounds(300, 10, 700, 700);
		LaminaBBDD milamina = new LaminaBBDD();
		add(milamina);
	}
	
}

class LaminaBBDD extends JPanel {
	
	public LaminaBBDD() {
		setLayout(new BorderLayout());
		
		comboTablas = new JComboBox<String>();
		areaInformacion = new JTextArea();
		
		comboTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombreTabla = (String) comboTablas.getSelectedItem();
				
				mostrarInfoTabla(nombreTabla);
			}
			
		});
		
		add(comboTablas, BorderLayout.NORTH);
		add(areaInformacion, BorderLayout.CENTER);
		
		conectarBBDD();
		obtenerTablas();
//		cerrarBBDD();
	}
	
	public void conectarBBDD() {
		
		miConexion = null;
		
		String datos[] = new String[3];
		
		try {
			
			URL fileLocation = getClass().getResource("datos_config.config");
			entrada = new FileReader(fileLocation.toString().substring(6));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "No se ha encontrado el archivo de conexi�n.");
			
		    JFileChooser chooser = new JFileChooser();
		    
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Archivos de texto", "txt");
		    
		    chooser.setFileFilter(filter);
		    
		    // La lamina es el componente padre
		    int returnVal = chooser.showOpenDialog(this);
		    
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	try {
					entrada = new FileReader(chooser.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "No se ha encontrado el archivo de conexi�n.");
					e1.printStackTrace();
				}
		    }
		    	
		}

			
		try {
			
			BufferedReader miBuffer = new BufferedReader(entrada);
			
			for(int i = 0; i <= 2; i++)
				datos[i] = miBuffer.readLine();
				
			miConexion = DriverManager.getConnection(datos[0], datos[1], datos[2]);
			
			entrada.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("NO CONECTA!!");	
//			e.printStackTrace();
//		}
		
	}
	
	public void obtenerTablas() {
		
		ResultSet miResultSet = null;
		
		try {
			
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			miResultSet = datosBBDD.getTables(null, null, null, null);
			
			while(miResultSet.next()) {
				comboTablas.addItem(miResultSet.getString("TABLE_NAME"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void mostrarInfoTabla(String tabla) {
		// TODO Auto-generated method stub
		ArrayList<String> campos = new ArrayList<String>();
		
		String consulta = "SELECT * FROM " + tabla;
		
		try {
			areaInformacion.setText("");
			
			Statement miStatement = miConexion.createStatement();
			
			ResultSet miResultSet = miStatement.executeQuery(consulta);
			
			ResultSetMetaData rsBBDD = miResultSet.getMetaData();
			
			for(int i = 1; i <= rsBBDD.getColumnCount(); i++) {
				
				campos.add(rsBBDD.getColumnLabel(i));
				
			}
			
			while(miResultSet.next()) {
				
				for(String campo: campos)
					areaInformacion.append(miResultSet.getString(campo) + " ");
				
				areaInformacion.append("\n");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cerrarBBDD() {
		
		try {
			miConexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private JComboBox<String> comboTablas;
	private JTextArea areaInformacion;
	
	private Connection miConexion;
	private FileReader entrada;
	
}