package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.EjecutaConsultas;
import vista.MarcoAplicacion;

public class ControladorBotonConsulta implements ActionListener {

	public ControladorBotonConsulta(MarcoAplicacion elMarco) {
		this.elMarco = elMarco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String seleccionSeccion = (String) elMarco.getSecciones().getSelectedItem();
		String seleccionPais = (String) elMarco.getPaises().getSelectedItem();
		resultadoConsulta = ejecutaConsultas.filtraBBDD(seleccionSeccion, seleccionPais);
		
		try {
			
			elMarco.getResultado().setText("");
			
			while(resultadoConsulta.next()) {
				elMarco.getResultado().append(resultadoConsulta.getString(1));
				elMarco.getResultado().append(", ");
				elMarco.getResultado().append(resultadoConsulta.getString(2));
				elMarco.getResultado().append(", ");
				elMarco.getResultado().append(resultadoConsulta.getString(3));
				elMarco.getResultado().append(", ");
				elMarco.getResultado().append(resultadoConsulta.getString(4));
				elMarco.getResultado().append("\n");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private MarcoAplicacion elMarco;
	private EjecutaConsultas ejecutaConsultas = new EjecutaConsultas();
	private ResultSet resultadoConsulta;
	
}