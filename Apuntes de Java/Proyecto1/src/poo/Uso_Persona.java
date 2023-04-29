package poo;

import java.util.Date;
import java.util.GregorianCalendar;

public class Uso_Persona {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona[] lasPersonas=new Persona[2];
		lasPersonas[0]= new Empleado2("Luis Conde",5000,2009,2,25);
		lasPersonas[1]= new Alumno("Javier","ingeniería");
		
		for(Persona p: lasPersonas) {
			System.out.println(p.dameNombre()+", "+p.dameDescripcion());
		}
	}
}

abstract class Persona{
	
	public Persona(String nom) {
		nombre=nom;
	}

	public String dameNombre() {
		return nombre;
	}
	public abstract String dameDescripcion();//metodo abstracto
	
	private String nombre;

}

class Empleado2 extends Persona{//heredamos de la clase padre
	
	public Empleado2(String nom, double sue, int agno,int mes, int dia) {
		super(nom);
		
		sueldo=sue;
		GregorianCalendar calendario = new GregorianCalendar(agno,mes-1,dia);
		altaContrato=calendario.getTime();
		Id=IdSiguiente;
		IdSiguiente++;
	}
	
	public String dameDescripcion() {//sobreescribir metodo abstacto del padre
		return "Este empleado tiene un Id= "+ Id+ " Con un sueldo de: "+sueldo;
	}
	
	
	public double dameSueldo() {//getter
		return sueldo;
	}
	public Date dameFechaContrato() {//getter
		return altaContrato;
	}
	public void subeSueldo(double porcentaje) {//setter
		double aumento=sueldo*porcentaje/100;
		sueldo+=aumento;
	}
	

	private double sueldo;
	private Date altaContrato;
	private int Id;
	private static int IdSiguiente=1;
}

class Alumno extends Persona{
	public Alumno(String nom, String car) {
		super(nom);
		carrera=car;
		
		
	}
	
	public String dameDescripcion() {//sobreescribir metodo abstacto del padre
		return "Este alumno está estudiando la carrera de:" + carrera;
	}
	private String carrera;
}
