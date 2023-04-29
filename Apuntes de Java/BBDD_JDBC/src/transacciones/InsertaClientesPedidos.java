package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// NOTA: primero importar las tablas clientes.sql y pedidos.sql (en ese orden) de este paquete

public class InsertaClientesPedidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection miConexion = null;

		try{					
			
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas?autoReconnect=true&useSSL=false", "root", "");
			Statement miStatement = miConexion.createStatement();
			
			miConexion.setAutoCommit(false);
			
		    String instruccionSql_1="INSERT INTO CLIENTES (C�DIGOCLIENTE, EMPRESA, DIRECCI�N) "
		    		+ "VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";
		    
		    miStatement.executeUpdate(instruccionSql_1);
		    
		    String instruccionSql_2="INSERT INTO PEDIDOS (N�MERODEPEDIDO, C�DIGOCLIENTE,FECHADEPEDIDO) "
		    		+ "VALUES ('82', 'CT84', '11/03/2000')";
		    
		    miStatement.executeUpdate(instruccionSql_2);
		    
		    miConexion.commit();
		    
		    System.out.println("Datos INSERTADOS correctamente.");
			
			miConexion.close();
		    
		} catch(Exception e){
			try {
				miConexion.rollback();
				miConexion.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			System.out.println("ERROR EN LA INSERCI�N DE DATOS!!");
			e.printStackTrace();

		}
		
	}

}