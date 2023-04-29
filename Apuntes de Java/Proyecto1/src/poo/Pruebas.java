package poo;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Empleados trabajador1= new Empleados("Paco");
		Empleados trabajador2= new Empleados("Ana");
		
		trabajador1.cambiaSeccion("RRHH");
		System.out.println(trabajador1.devuelveDatos()+"\n"+trabajador2.devuelveDatos());		
		//System.out.println(trabajador2.devuelveDatos());
		System.out.println(Empleados.dameIdSiguiente());
		
	}
}

class Empleados{
	public Empleados(String nom) {
		nombre=nom;
		seccion="Administración";
		Id=IdSiguiente;
		IdSiguiente++;
		
	}
	
	public void cambiaSeccion(String seccion) {//setter
		this.seccion=seccion;
	}
	public String devuelveDatos() {//getter
		return "El nombre es: "+nombre+" y la seccion es: "+seccion+ " y el Id: "+Id;
	}
	
	public static String dameIdSiguiente() {//METODO ESTATICO
		return "El Id siguiente es: "+IdSiguiente;
	}
	
	
	private final String nombre; //constante
	private String seccion;
	private int Id;
	private static int IdSiguiente=1;
}


