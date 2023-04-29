package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CargaMenus {

	public CargaMenus() {
		miConexion = new Conexion();
	}
	
	public void ejecutaConsultas() {

		Connection accesoBBDD = miConexion.dameConexion();
		
		try {
			
			// Si usamos el mismo statement salta un error
			Statement secciones = accesoBBDD.createStatement();
			Statement paises = accesoBBDD.createStatement();
			rsSecciones = secciones.executeQuery("SELECT DISTINCTROW SECCI�N FROM PRODUCTOS");
			rsPaises = paises.executeQuery("SELECT DISTINCTROW PA�SDEORIGEN FROM PRODUCTOS");
			
			// accesoBBDD.close();
			// Aca se deberia poder cerrar la conexion pero tira un error
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ResultSet getRsPaises() {
		return rsPaises;
	}

	public ResultSet getRsSecciones() {
		return rsSecciones;
	}
	
	private Conexion miConexion;
	private ResultSet rsPaises;
	private ResultSet rsSecciones;
	
}
