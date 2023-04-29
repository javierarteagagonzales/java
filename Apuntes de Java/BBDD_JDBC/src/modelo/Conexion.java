package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	Connection miConexion = null;
	
	public Conexion() {
		
	}
	
	public Connection dameConexion() {
		
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas?autoReconnect=true&useSSL=false", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
		
		return miConexion;
		
	}
	
}