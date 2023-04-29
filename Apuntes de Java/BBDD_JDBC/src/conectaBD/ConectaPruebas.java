package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaPruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
		// 1. CREAR CONEXI�N
			
			// Para ver el host y puerto:
			// SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port')
			
			// Funciona, con una advertencia
			// Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			
			// Solucion: con agregar a la URL "?useSSL=false" al final funciona
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas?autoReconnect=true&useSSL=false", "root", "");
			
		// 2. CREAR OBJETO STATEMENT
			
			Statement miStatement = miConexion.createStatement();
			
		// 3. EJECUTAR SQL
			
			// Ver en la API de Java los distintos metodos get, dependiendo el tipo 
			// que tenga la columna de la tabla
			ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM PRODUCTOS");
			
		// 4. RECORRER EL RESULTSET
			
			while(miResultSet.next()) {
				System.out.println(miResultSet.getString("C�DIGOART�CULO") + "\t"
						+ miResultSet.getString("NOMBREART�CULO") + " - "
						+ miResultSet.getString("PRECIO"));
				
				// Con nro de columna (no recomendado)
//				System.out.println(miResultSet.getString(1) + "\t"
//						+ miResultSet.getString(3) + " - "
//						+ miResultSet.getString(4));
			}
			
		// 5. LIBERAR MEMORIA
			
			miResultSet.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
		
	}

}
