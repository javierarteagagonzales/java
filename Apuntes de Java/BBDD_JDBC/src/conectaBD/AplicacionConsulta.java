package conectaBD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AplicacionConsulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mimarco=new MarcoAplicacion();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
	}

}

class MarcoAplicacion extends JFrame{
	
	public MarcoAplicacion(){
		setTitle("Consulta BBDD");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());
		
		secciones = new JComboBox<String>();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		
		paises = new JComboBox<String>();
		paises.setEditable(false);
		paises.addItem("Todos");
		
		menus.add(secciones);
		menus.add(paises);
		add(menus, BorderLayout.NORTH);
		
		resultado = new JTextArea(4, 50);
		resultado.setEditable(false);
		resultado.setLineWrap(true);
		add(resultado, BorderLayout.CENTER);
		
		scroll = new JScrollPane(resultado);
		add(scroll);
		
		JButton botonConsulta = new JButton("Consulta");
		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutaConsulta();
			}
			
		});
		add(botonConsulta, BorderLayout.SOUTH);
		
		/* ********** CONEXI�N CON BASE DE DATOS ********** */
		
		try {
			
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas?autoReconnect=true&useSSL=false", "root", "");
			Statement sentencia = miConexion.createStatement();
			
			/* ********** CARGA JCOMBOBOX SECCIONES ********** */
			
			String consulta = "SELECT DISTINCTROW SECCI�N FROM PRODUCTOS";
			ResultSet rs = sentencia.executeQuery(consulta);
			
			while(rs.next()) {
				secciones.addItem(rs.getString(1));
			}
			
			rs.close();
			
			/* ********** CARGA JCOMBOBOX PAISES ********** */
			
			consulta = "SELECT DISTINCTROW PA�SDEORIGEN FROM PRODUCTOS";
			rs = sentencia.executeQuery(consulta);
			
			while(rs.next()) {
				paises.addItem(rs.getString(1));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("NO CONECTA!!");
			
			e.printStackTrace();
		}
		
	}
	
	private void ejecutaConsulta() {
		
		try {
			resultado.setText("");
			ResultSet rs = null;
			String seccion = (String) secciones.getSelectedItem();
			String pais = (String) paises.getSelectedItem();
			
			if(!seccion.equals("Todos") && pais.equals("Todos")){
				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();
			} else if(seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();
			} else if(!seccion.equals("Todos") && !pais.equals("Todos")){
				enviaConsultaAmbos = miConexion.prepareStatement(consultaAmbos);
				enviaConsultaAmbos.setString(1, seccion);
				enviaConsultaAmbos.setString(2, pais);
				rs = enviaConsultaAmbos.executeQuery();
			} else if(seccion.equals("Todos") && pais.equals("Todos")){
				enviaConsultaTodos = miConexion.createStatement();
				rs = enviaConsultaTodos.executeQuery(consultaTodos);
			}
			
			while(rs.next()){
				resultado.append(rs.getString(1));
				resultado.append(", ");
				resultado.append(rs.getString(2));
				resultado.append(", ");
				resultado.append(rs.getString(3));
				resultado.append(", ");
				resultado.append(rs.getString(4));
				resultado.append("\n");
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Connection miConexion;
	private Statement enviaConsultaTodos;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaAmbos;
	
	private final String consultaTodos = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN"
			+ " FROM PRODUCTOS";
	private final String consultaSeccion = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN"
			+ " FROM PRODUCTOS WHERE SECCI�N=?";
	private final String consultaPais = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN"
			+ " FROM PRODUCTOS WHERE PA�SDEORIGEN=?";
	private final String consultaAmbos = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN"
			+ " FROM PRODUCTOS WHERE SECCI�N=? AND PA�SDEORIGEN=?";
	
	private JComboBox<String> secciones;
	private JComboBox<String> paises;
	private JTextArea resultado;
	private JScrollPane scroll;
	
}