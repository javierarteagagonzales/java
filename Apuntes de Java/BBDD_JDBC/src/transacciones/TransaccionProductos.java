package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TransaccionProductos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection miConexion = null;

		try{					
			
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas?autoReconnect=true&useSSL=false", "root", "");
			Statement miStatement = miConexion.createStatement();
			
			miConexion.setAutoCommit(false);
			
		    String instruccionSql_1="DELETE FROM PRODUCTOS WHERE PA�SDEORIGEN='ITALIA'";
		    String instruccionSql_2="DELETE FROM PRODUCTOS WHERE PRECIO>300";
		    String instruccionSql_3="UPDATE PRODUCTOS SET PRECIO=PRECIO*1.15";	// precio debe ser de tipo numerico, caso contrario dara error
		    
		    boolean ejecutar = ejecutarTransaccion();
		    
		    if(ejecutar) {
		    	
			    miStatement.executeUpdate(instruccionSql_1);
			    miStatement.executeUpdate(instruccionSql_2);
			    miStatement.executeUpdate(instruccionSql_3);
			    miConexion.commit();
			    
			    System.out.println("Se ejecut� la transacci�n correctamente.");
			    
		    } else {
		    	
			    System.out.println("No se realiz� cambio alguno en la BBDD.");
			    
		    }
			
			miConexion.close();
		    
		} catch(Exception e){
			try {
				miConexion.rollback();
				miConexion.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			System.out.println("Algo sali� mal y no se realiz� cambio alguno en la BBDD.");
			e.printStackTrace();

		}
		
	}

	private static boolean ejecutarTransaccion() {
		int ejecucion = JOptionPane.showConfirmDialog(null,
	             "�Ejecutamos transacci�n?", "BBDD", JOptionPane.YES_NO_OPTION);
		if(ejecucion == 0)
			return true;
		else
			return false;
	}

}
