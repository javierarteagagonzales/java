package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjecutaConsultas {

	public EjecutaConsultas() {
		miConexion = new Conexion();
	}
	
	public ResultSet filtraBBDD(String seccion, String pais) {
		
		Connection conecta = miConexion.dameConexion();
		rs = null;
		
		try {
			
			if(!seccion.equals("Todos") && pais.equals("Todos")){
				
				enviaConsulta = conecta.prepareStatement(consultaSeccion);
				enviaConsulta.setString(1, seccion);
				rs = enviaConsulta.executeQuery();
				
			} else if(seccion.equals("Todos") && !pais.equals("Todos")){

				enviaConsulta = conecta.prepareStatement(consultaPais);
				enviaConsulta.setString(1, pais);
				rs = enviaConsulta.executeQuery();
				
			} else if(!seccion.equals("Todos") && !pais.equals("Todos")){

				enviaConsulta = conecta.prepareStatement(consultaAmbos);
				enviaConsulta.setString(1, seccion);
				enviaConsulta.setString(2, pais);
				rs = enviaConsulta.executeQuery();
				
			} else if(seccion.equals("Todos") && pais.equals("Todos")) {
				
				enviaConsultaTodos = conecta.createStatement();
				rs = enviaConsultaTodos.executeQuery(consultaTodos);
				
			}

//			conecta.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	private Conexion miConexion;
	private ResultSet rs;
	private Statement enviaConsultaTodos;
	private PreparedStatement enviaConsulta;

	private final String consultaTodos = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN"
			+ " FROM PRODUCTOS";
	private final String consultaSeccion = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN"
			+ " FROM PRODUCTOS WHERE SECCIÓN=?";
	private final String consultaPais = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN"
			+ " FROM PRODUCTOS WHERE PAÍSDEORIGEN=?";
	private final String consultaAmbos = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN"
			+ " FROM PRODUCTOS WHERE SECCIÓN=? AND PAÍSDEORIGEN=?";
	
}
